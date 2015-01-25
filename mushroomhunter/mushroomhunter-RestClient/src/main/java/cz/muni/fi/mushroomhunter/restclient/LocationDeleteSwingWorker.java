/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.mushroomhunter.restclient;

import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import cz.muni.fi.mushroomhunter.restclient.MushroomDeleteSwingWorker.HttpEntityEnclosingDeleteRequest;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author Simon Hochla
 */
public class LocationDeleteSwingWorker extends SwingWorker<Integer, Void> {

    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }
    
    private final RestClient restClient;

    public LocationDeleteSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int selectedRow = restClient.getTblLocation().getSelectedRow();
        RestTemplate restTemplate = new RestTemplate();
        
        String plainCreds = RestClient.USER_NAME + ":" + RestClient.PASSWORD;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<String> request = new HttpEntity<>(headers);
        
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory() {
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        });
        
        restTemplate.exchange(
                RestClient.SERVER_URL + "pa165/rest/location/" + RestClient.getLocationIDs().get(selectedRow),
                HttpMethod.DELETE,
                request,
                LocationDto.class);
        
        RestClient.getLocationIDs().remove(selectedRow);
        return selectedRow;
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (Exception e) {
            restClient.getlMessageLocations().setText("ERROR: server is unavailable.");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) restClient.getTblLocation().getModel();
        try {
            model.removeRow(get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
