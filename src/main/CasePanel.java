package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class CasePanel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 800;
    final int PANEL_HEIGHT = 800;
    double x = 0;
    double y = 0;
    double[] vel = {2.0, 5.8};
    int w = 100;
    int h = 100;

    Timer timer;

    CasePanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        timer = new Timer(10, this); //updates animation every 10ms
        timer.start();

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        //graphComponent.setCenterPage(true);
        graphComponent.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //graphComponent.setAlignmentY(0.0f);
        this.add(graphComponent);
    }

    public void paint(Graphics g) {
        super.paint(g); //paint background

        Graphics2D g2d = (Graphics2D) g;

        //drawing of rectangle
        g2d.setColor(new Color(123, 25, 250));
        g2d.fillRect((int)x, (int)y, w, h);

        //TODO:: drawing of opinion
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //animating rectangle
        x += vel[0];
        y += vel[1];

        if (x+w >= PANEL_WIDTH || x < 0) {
            vel[0] = -vel[0];
        }
        if (y+h >= PANEL_HEIGHT || y < 0) {
            vel[1] = -vel[1];
        }

        //TODO:: animation of opinion

        repaint(); //flushes and recalls paint(g) function.
    }
}
