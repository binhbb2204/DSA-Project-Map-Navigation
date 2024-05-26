package worker;

import javax.swing.SwingWorker;
import UI.frmMap;
import data.RoutingData;
import data.RoutingService;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Worker extends SwingWorker<Void, Void> {
    private final double fromLat;
    private final double fromLon;
    private final double toLat;
    private final double toLon;
    private final frmMap instance;

    public Worker(double fromLat, double fromLon, double toLat, double toLon, frmMap frmMapInstance) {
        this.fromLat = fromLat;
        this.fromLon = fromLon;
        this.toLat = toLat;
        this.toLon = toLon;
        this.instance = frmMapInstance;
    }

    @Override
    protected Void doInBackground() {
        try {
            instance.setRoutingInProgress(true); // Set flag to indicate routing is in progress
            List<RoutingData> routingData = RoutingService.getInstance().routing(fromLat, fromLon, toLat, toLon);
            instance.updateRouting(routingData); // Update GUI with routing data
        } catch (Exception e) {
            // Handle routing failure
            e.printStackTrace(); // Log the exception or display an error message
        } finally {
            instance.setRoutingInProgress(false); // Set flag to indicate routing is complete
        }
        return null;
    }



    @Override
    protected void done() {
        try {
            get(); // Ensure that doInBackground() is completed
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace(); // Handle exceptions
        }
    }
}
