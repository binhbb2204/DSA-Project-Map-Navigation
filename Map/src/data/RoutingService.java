package data;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.Translation;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoutingService {
    private static RoutingService instance;
    private final GraphHopper hopper;

    public static RoutingService getInstance() {
        if (instance == null) {
            instance = new RoutingService();
        }
        return instance;
    }

    private RoutingService() {
        hopper = createGraphHopperInstance("src/files/vietnam-latest.osm");
    }

    private GraphHopper createGraphHopperInstance(String ghLoc) {
        GraphHopper graHopper = new GraphHopper();
        graHopper.setOSMFile(ghLoc);
        graHopper.setGraphHopperLocation("src/target/routing-graph-cache");

        // Configure profiles
        graHopper.setProfiles(new Profile("car").setVehicle("car").setWeighting("fastest").setTurnCosts(false));
        graHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));

        // Import or load graph data
        graHopper.importOrLoad();
        return graHopper;
    }

    public List<RoutingData> routing(double fromLat, double fromLon, double toLat, double toLon) {
        // Configure routing request
        GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon).setProfile("car").setLocale(Locale.US);
        GHResponse rsp = hopper.route(req);

        // Handle routing errors
        if (rsp.hasErrors()) {
            throw new RuntimeException(rsp.getErrors().toString());
        }

        // Extract routing data from response
        ResponsePath path = rsp.getBest();
        PointList pointList = path.getPoints();
        Translation tr = hopper.getTranslationMap().getWithFallBack(Locale.UK);
        InstructionList il = path.getInstructions();

        // Create list of routing data
        List<RoutingData> list = new ArrayList<>();
        for (Instruction instruction : il) {
            list.add(new RoutingData(instruction.getDistance(), instruction.getTurnDescription(tr), instruction.getPoints()));
        }
        return list;
    }
}
