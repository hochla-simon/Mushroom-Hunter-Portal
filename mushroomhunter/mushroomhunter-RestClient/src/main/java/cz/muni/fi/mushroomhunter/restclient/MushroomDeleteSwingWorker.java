/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.mushroomhunter.restclient;

import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Simon
 */
public class MushroomDeleteSwingWorker extends SwingWorker<Integer, Void> {

    private RestClient restClient;

    public MushroomDeleteSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    protected Integer doInBackground() throws Exception {
        int selectedRow = restClient.getTblMushroom().getSelectedRow();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(restClient.SERVER_URL + "pa165/rest/mushroom/" + restClient.getMushroomIDs().get(selectedRow));
        restClient.getMushroomIDs().remove(selectedRow);
        return selectedRow;
    }

    protected void done() {
        try {
            get();
        } catch (Exception e) {
            restClient.getlMessageMushrooms().setText("ERROR: server is unavailable.");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) restClient.getTblMushroom().getModel();
        try {
            model.removeRow(get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
