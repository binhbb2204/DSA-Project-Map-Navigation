package worker;

import javax.swing.SwingWorker;
import UI.frmMap;
import org.jxmapviewer.viewer.GeoPosition;
import java.util.*;
import java.util.concurrent.ExecutionException;
import data.*;

public class Worker extends SwingWorker<List<RoutingData>, Void> {
        private final GeoPosition start;
        private final GeoPosition end;
        private final frmMap main;

        public Worker(GeoPosition start, GeoPosition end, frmMap main) {
            this.start = start;
            this.end = end;
            this.main = main;
        }

        @Override
        protected List<RoutingData> doInBackground() throws Exception {
            main.setRoutingInProgress(true); // Set flag to indicate routing is in progress
            return RoutingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());
        }

        @Override
        protected void done() {
            main.setRoutingInProgress(false); // Set flag to indicate routing is complete
            try {
                List<RoutingData> result = get(); // Retrieve routing data from doInBackground()
                main.updateRouting(result); // Update GUI with routing results
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace(); // Handle exceptions
            }
        }
}
