package waypoint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class MyWaypoint extends DefaultWaypoint {
    private String name;
    private JButton button;

    public MyWaypoint(String name,  GeoPosition coord) {
        super(coord);
        this.name = name;
        initButton();
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
    
    private void initButton() {
        button = new ButtonWaypoint();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click: " + name);
            }
        });
    }
  
}
