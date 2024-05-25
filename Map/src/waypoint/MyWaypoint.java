package waypoint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class MyWaypoint extends DefaultWaypoint {
    private String name;
    private JButton button;
    private PointType pointType;

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public MyWaypoint(String name, PointType pointType, EventWaypoint event, GeoPosition coord) {
        super(coord);
        this.name = name;
        initButton(event);
        this.pointType = pointType;
    }
   
    public String getName() {
        return name;
    }

    public JButton getButton() {
        return button;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
    
    public MyWaypoint() {
        
    }
    
    private void initButton(EventWaypoint event) {
        button = new ButtonWaypoint();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.selected(MyWaypoint.this);
            }
        });
    }
    
    public static enum PointType {
        START,END
    }
  
}
