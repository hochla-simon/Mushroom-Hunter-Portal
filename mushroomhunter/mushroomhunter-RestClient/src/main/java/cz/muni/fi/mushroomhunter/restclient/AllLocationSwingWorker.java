/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.mushroomhunter.restclient;

import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Simon
 */
public class AllLocationSwingWorker extends SwingWorker<List<LocationDto>, Void> {

    private final RestClient restClient;

    public AllLocationSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected List<LocationDto> doInBackground() throws Exception {
        String plainCreds = RestClient.USER_NAME + ":" + RestClient.PASSWORD;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LocationDto[]> responseEntity = restTemplate.exchange(
                RestClient.SERVER_URL + "pa165/rest/location/", HttpMethod.GET, request, LocationDto[].class);
        LocationDto[] locationDtoArray = responseEntity.getBody();
        List<LocationDto> locationDtoList = new ArrayList<>();
        locationDtoList.addAll(Arrays.asList(locationDtoArray));
        return locationDtoList;
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (Exception e) {
            restClient.getlMessageLocations().setText("ERROR: server is unavailable.");
            return;
        }
        try {
            List<LocationDto> list = get();
            DefaultTableModel model = (DefaultTableModel) restClient.getTblLocation().getModel();
            model.setRowCount(0);
            for (LocationDto list1 : list) {
                RestClient.getLocationIDs().add(list1.getId());
                model.addRow(new Object[]{list1.getName(), list1.getDescription(), list1.getNearCity()});
            }
        } catch (ExecutionException ex) {

        } catch (InterruptedException ex) {
            throw new RuntimeException("Operation interrupted", ex);
        }
    }
}
