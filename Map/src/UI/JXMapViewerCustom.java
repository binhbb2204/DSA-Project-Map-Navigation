package UI;

import com.graphhopper.util.shapes.GHPoint3D;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import data.RoutingData;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

public class JXMapViewerCustom extends JXMapViewer {
    private List<RoutingData> routingData;

    public List<RoutingData> getRoutingData() {
        return routingData;
    }

    public void setRoutingData(List<RoutingData> routingData) {
        this.routingData = routingData;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (routingData != null && !routingData.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Path2D p2 = new Path2D.Double(); // Create Path2D object outside the loop
            for (RoutingData d : routingData) {
                draw(p2, d); // Call draw method for each routing data
            }
            g2.setColor(new Color(28, 23, 255));
            g2.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.draw(p2); // Draw the entire path at once
            g2.dispose();
        }
    }

    private void draw(Path2D p2, RoutingData d) {
        System.out.println("Drawing path...");
        boolean first = true;
        for (GHPoint3D point : d.getPointList()) {
            System.out.println("Point: " + point.toString());
            Point2D point2D = convertGeoPositionToPoint(new GeoPosition(point.getLat(), point.getLon()));
            if (first) {
                System.out.println("MoveTo: " + point2D.toString());
                p2.moveTo(point2D.getX(), point2D.getY());
                first = false;
            } else {
                System.out.println("LineTo: " + point2D.toString());
                p2.lineTo(point2D.getX(), point2D.getY());
            }
        }
    }
}
