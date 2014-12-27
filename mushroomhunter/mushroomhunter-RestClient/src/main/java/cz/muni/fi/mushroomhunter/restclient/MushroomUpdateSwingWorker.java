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
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Simon
 */
public class MushroomUpdateSwingWorker extends SwingWorker<Integer, Void> {

    private RestClient restClient;

    public MushroomUpdateSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        DefaultTableModel model = (DefaultTableModel) restClient.getTblMushroom().getModel();
        int selectedRow = restClient.getTblMushroom().getSelectedRow();
        RestTemplate restTemplate = new RestTemplate();
        MushroomDto mushroomDto
                = restTemplate.getForObject(restClient.SERVER_URL + "pa165/rest/mushroom/" + restClient.getMushroomIDs().get(selectedRow), MushroomDto.class);

        mushroomDto.setName(restClient.getTfMushroomName().getText());

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy", new Locale("en_US"));

        //to create date object only month is used, day and year are fixed values
        String dateInString = "01-" + restClient.getComboBoxMushroomStartOfOccurence().getSelectedItem().toString() + "-2000";
        mushroomDto.setStartOfOccurence(formatter.parse(dateInString));

        //to create date object only month is used, day and year are fixed values
        dateInString = "01-" + restClient.getComboBoxMushroomEndOfOccurence().getSelectedItem().toString() + "-2000";
        mushroomDto.setEndOfOccurence(formatter.parse(dateInString));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(mushroomDto);
        HttpEntity request = new HttpEntity(json, headers);

        ResponseEntity<MushroomDto> newmushroom
                = restTemplate.exchange(restClient.SERVER_URL + "pa165/rest/mushroom", HttpMethod.PUT, request, MushroomDto.class);
        return selectedRow;
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
        try {
            model.setValueAt(restClient.getTfMushroomName().getText(), get(), 0);
            model.setValueAt(restClient.getComboBoxMushroomType().getSelectedItem().toString(), get(), 1);
            model.setValueAt(restClient.getComboBoxMushroomStartOfOccurence().getSelectedItem().toString(), get(), 2);
            model.setValueAt(restClient.getComboBoxMushroomEndOfOccurence().getSelectedItem().toString(), get(), 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
