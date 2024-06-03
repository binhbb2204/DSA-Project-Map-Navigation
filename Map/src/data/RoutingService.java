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
import com.graphhopper.util.shapes.GHPoint;

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
        hopper = createGraphHopperInstance("osm file/vietnam-latest.osm.pbf"); // Update this line with the correct path
    }

    private GraphHopper createGraphHopperInstance(String ghLoc) {
        GraphHopper graHopper = new GraphHopper();
        graHopper.setOSMFile(ghLoc);
        graHopper.setGraphHopperLocation("target/routing-graph-cache");
        graHopper.setProfiles(new Profile("car").setVehicle("car").setWeighting("fastest").setTurnCosts(false));
        graHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));
        graHopper.setGraphHopperLocation("target/routing-graph-cache");
        graHopper.importOrLoad();
        return graHopper;
    }

    public List<RoutingData> routing(double fromLat, double fromLon, double toLat, double toLon) {
        System.out.println("Routing from " + fromLat + ", " + fromLon + " to " + toLat + ", " + toLon);

        GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon).
                setProfile("car").
                setLocale(Locale.US);
        GHResponse rsp = hopper.route(req);

        if (rsp.hasErrors()) {
            throw new RuntimeException(rsp.getErrors().toString());
        }
        // use the best path, see the GHResponse class for more possibilities.
        ResponsePath path = rsp.getBest();

        // points, distance in meters and time in millis of the full path
        PointList pointList = path.getPoints();
        double distance = path.getDistance();
        long timeInMs = path.getTime();

        Translation tr = hopper.getTranslationMap().getWithFallBack(Locale.UK);
        InstructionList il = path.getInstructions();
        // iterate over all turn instructions
        List<RoutingData> list = new ArrayList<>();
        for (Instruction instruction : il) {
            // System.out.println("distance " + instruction.getDistance() + " for instruction: " + instruction.getTurnDescription(tr));
            list.add(new RoutingData(instruction.getDistance(), instruction.getTurnDescription(tr), instruction.getPoints()));
        }
        System.out.println("Routing completed successfully");

        return list;
    }
}
