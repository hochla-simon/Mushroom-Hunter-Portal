/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.mushroomhunter.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cz.fi.muni.pa165.mushroomhunter.api.dto.LocationDto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Simon
 */
public class LocationUpdateSwingWorker extends SwingWorker<Integer, Void> {

    private RestClient restClient;

    public LocationUpdateSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        DefaultTableModel model = (DefaultTableModel) restClient.getTblLocation().getModel();
        int selectedRow = restClient.getTblLocation().getSelectedRow();

        RestTemplate restTemplate = new RestTemplate();
        LocationDto locationDto = restTemplate.getForObject(restClient.SERVER_URL + "pa165/rest/location/" + restClient.getLocationIDs().get(selectedRow), LocationDto.class);

        locationDto.setName(restClient.getTfLocationName().getText());
        locationDto.setDescription(restClient.getTfLocationDescription().getText());
        locationDto.setNearCity(restClient.getTfLocationNearCity().getText());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(locationDto);
        HttpEntity request = new HttpEntity(json, headers);

        restTemplate.exchange(restClient.SERVER_URL + "pa165/rest/location", HttpMethod.PUT, request, LocationDto.class);
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
            model.setValueAt(restClient.getTfLocationName().getText(), get(), 0);
            model.setValueAt(restClient.getTfLocationDescription().getText(), get(), 1);
            model.setValueAt(restClient.getTfLocationNearCity().getText(), get(), 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
