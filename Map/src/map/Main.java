 
package map;


import java.util.HashSet;
import java.util.Set;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import waypoint.MyWaypoint;
import waypoint.WaypointRender;

public class Main extends javax.swing.JFrame {

    private final Set<MyWaypoint> waypoints = new HashSet<>();
    
    public Main() {
        initComponents();
        init();
    }
    
    private void init(){
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(10.87805364388436, 106.80156058652832);
        jXMapViewer.setAddressLocation(geo);
        jXMapViewer.setZoom(2000);

        // Making Mouse zooming
        MouseInputListener mMouse = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mMouse);
        jXMapViewer.addMouseMotionListener(mMouse);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer));

    }
     
    private void addWaypoint(MyWaypoint waypoint) {
        for(MyWaypoint d: waypoints) {
            jXMapViewer.remove(d.getButton());
        }
        waypoints.add(waypoint);
        initWaypoint();
    }
    
    private void initWaypoint(){
        WaypointPainter<MyWaypoint> wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        jXMapViewer.setOverlayPainter(wp);
        for(MyWaypoint d:waypoints) {
            jXMapViewer.add(d.getButton());
        }
    }
    
    private void clearWaypoint() {
        for (MyWaypoint d:waypoints) {
            jXMapViewer.remove(d.getButton());
        }
        waypoints.clear();
        initWaypoint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXMapViewer = new org.jxmapviewer.JXMapViewer();
        comboMapType = new javax.swing.JComboBox<>();
        cmdAdd = new javax.swing.JButton();
        cmdClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        comboMapType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        comboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D Basemap (Roadmap)", "Satellite", "Terrain", "Virtual Earth" }));
        comboMapType.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(204, 204, 204))));
        comboMapType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMapTypeActionPerformed(evt);
            }
        });

        cmdAdd.setText("Add Waypoint");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdClear.setText("Clear Waypoint");
        cmdClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXMapViewerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdAdd)
                .addGap(12, 12, 12)
                .addComponent(cmdClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXMapViewerLayout.createSequentialGroup()
                .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdAdd)
                        .addComponent(cmdClear)))
                .addGap(0, 396, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        addWaypoint(new MyWaypoint("Test 001", new GeoPosition(10.877637469612493, 106.80157667978177 )));
        initWaypoint();
    }//GEN-LAST:event_cmdAddActionPerformed

    private void cmdClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearActionPerformed
        clearWaypoint();
    }//GEN-LAST:event_cmdClearActionPerformed

    private void comboMapTypeActionPerformed(java.awt.event.ActionEvent evt) {
        TileFactoryInfo info = null;
        int index = comboMapType.getSelectedIndex();
        if(index == 0 ){
            info = new OSMTileFactoryInfo();
        }
        else if(index == 1){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }
        else if(index == 2){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        }
        else if(index == 3){
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        }
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
    }                                            

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdClear;
    private javax.swing.JComboBox<String> comboMapType;
    private org.jxmapviewer.JXMapViewer jXMapViewer;
    // End of variables declaration//GEN-END:variables
}
