/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.mushroomhunter.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Simon Hochla
 */
public class MushroomCreateSwingWorker extends SwingWorker<Void, Void> {

    private final RestClient restClient;

    public MushroomCreateSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected Void doInBackground() throws Exception {
        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setName(restClient.getTfMushroomName().getText());

        mushroomDto.setType(cz.fi.muni.pa165.mushroomhunter.api.Type.valueOf(restClient.getComboBoxMushroomType().getSelectedItem().toString()));

        //to create date object only month is used, day and year are fixed values
        String dateInString = restClient.getComboBoxMushroomStartOfOccurence().getSelectedItem().toString() + " 1, 2000";

        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy", new Locale("en_US"));

        mushroomDto.setStartOfOccurence(formatter.parse(dateInString));

        //to create date object only month is used, day and year are fixed values
        dateInString = restClient.getComboBoxMushroomEndOfOccurence().getSelectedItem().toString() + " 1, 2000";
        mushroomDto.setEndOfOccurence(formatter.parse(dateInString));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.ALL);
        headers.setAccept(mediaTypeList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mushroomDto);
        
        String plainCreds = RestClient.USER_NAME + ":" + RestClient.PASSWORD;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        headers.add("Authorization", "Basic " + base64Creds);
        
        HttpEntity request = new HttpEntity(json, headers);

        RestTemplate restTemplate = new RestTemplate();
        Long[] result = restTemplate.postForObject(RestClient.SERVER_URL + "pa165/rest/mushroom", request, Long[].class);

        System.out.println("Id of the created mushroom: " + result[0]);
        RestClient.getMushroomIDs().add(result[0]);
        return null;
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (Exception e) {
            restClient.getlMessageMushrooms().setText("ERROR: server is unavailable.");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) restClient.getTblMushroom().getModel();
        model.addRow(new Object[]{restClient.getTfMushroomName().getText(), restClient.getComboBoxMushroomType().getSelectedItem().toString(),
            restClient.getComboBoxMushroomStartOfOccurence().getSelectedItem().toString(),
            restClient.getComboBoxMushroomEndOfOccurence().getSelectedItem().toString()});
    }
}
