/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.mushroomhunter.restclient;

import cz.fi.muni.pa165.mushroomhunter.api.dto.MushroomDto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Simon
 */
public class AllMushroomsSwingWorker extends SwingWorker<List<MushroomDto>, Void> {

    private final RestClient restClient;

    public AllMushroomsSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    protected List<MushroomDto> doInBackground() throws Exception {
        String plainCreds = RestClient.USER_NAME + ":" + RestClient.PASSWORD;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        
        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MushroomDto[]> responseEntity = restTemplate.exchange(
                RestClient.SERVER_URL + "pa165/rest/mushroom/", HttpMethod.GET, request, MushroomDto[].class);
        MushroomDto[] mushroomDtoArray = responseEntity.getBody();
        List<MushroomDto> mushroomDtoList = new ArrayList<>();
        mushroomDtoList.addAll(Arrays.asList(mushroomDtoArray));
        return mushroomDtoList;
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (Exception e) {
            restClient.getlMessageMushrooms().setText("ERROR: server is unavailable.");
            return;
        }
        try {
            List<MushroomDto> list = get();
            DefaultTableModel model = (DefaultTableModel) restClient.getTblMushroom().getModel();
            model.setRowCount(0);
            for (int i = 0; i < list.size(); i++) {
                RestClient.getMushroomIDs().add(list.get(i).getId());

                SimpleDateFormat MMMMFormat = new SimpleDateFormat("MMMM", new Locale("en_US"));
                String startOfOccurence = MMMMFormat.format(list.get(i).getStartOfOccurence());
                String endOfOccurence = MMMMFormat.format(list.get(i).getEndOfOccurence());

                model.addRow(new Object[]{list.get(i).getName(), list.get(i).getType().toString(),
                    startOfOccurence, endOfOccurence});
            }
        } catch (ExecutionException ex) {

        } catch (InterruptedException ex) {
            throw new RuntimeException("Operation interrupted", ex);
        }
    }
}
