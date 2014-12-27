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
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Simon
 */
public class LocationCreateSwingWorker extends SwingWorker<Void, Void> {

    RestClient restClient;

    public LocationCreateSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected Void doInBackground() throws Exception {
        LocationDto locationDto = new LocationDto();
        locationDto.setName(restClient.getTfLocationName().getText());
        locationDto.setDescription(restClient.getTfLocationDescription().getText());
        locationDto.setNearCity(restClient.getTfLocationNearCity().getText());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.ALL);
        headers.setAccept(mediaTypeList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(locationDto);
        HttpEntity request = new HttpEntity(json, headers);

        RestTemplate restTemplate = new RestTemplate();
        Long[] result = restTemplate.postForObject(restClient.SERVER_URL + "pa165/rest/location", request, Long[].class);

        restClient.getLocationIDs().add(result[0]);
        return null;
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
        model.addRow(new Object[]{restClient.getTfLocationName().getText(), restClient.getTfLocationDescription().getText(), restClient.getTfLocationNearCity().getText()});
    }
}
