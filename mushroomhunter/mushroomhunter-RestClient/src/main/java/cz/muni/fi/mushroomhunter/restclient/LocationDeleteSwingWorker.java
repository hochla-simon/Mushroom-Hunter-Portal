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
public class LocationDeleteSwingWorker extends SwingWorker<Integer, Void> {

    private RestClient restClient;

    public LocationDeleteSwingWorker(RestClient restClient) {
        this.restClient = restClient;
    }

    protected Integer doInBackground() throws Exception {
        int selectedRow = restClient.getTblLocation().getSelectedRow();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(restClient.SERVER_URL + "pa165/rest/location/" + RestClient.getLocationIDs().get(selectedRow));
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
