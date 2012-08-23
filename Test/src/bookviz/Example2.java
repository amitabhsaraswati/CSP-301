package bookviz;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;

public class Example2 {

	private static Graph graph;
    private static Visualization vis;
    private static Display d;
    public static void main(String[] argv) throws IOException{
    	Parser a = new Parser();
    	graph = a.parseblog();
    	setUpVisualization();
		setUPRenderers();
		setUpActions();
		setUpDisplay();
		JFrame frame = new JFrame("graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(d);
		frame.pack();
		frame.setVisible(true);
		vis.run("color");
		vis.run("layout");
    	}
    private static void setUpDisplay() {
		d = new Display(vis);
		d.setSize(720, 500);
		d.addControlListener(new DragControl());
		d.addControlListener(new PanControl());
		d.addControlListener(new ZoomControl());
		d.addControlListener(new Hover());
		d.setBackground(Color.WHITE);
	}
    
    private static void setUPRenderers() {
		ShapeRenderer r = new ShapeRenderer();
		vis.setRendererFactory(new DefaultRendererFactory(r));
	}


	private static void setUpVisualization() {
		vis = new Visualization();
		vis.add("blog", graph);
	}
	
	private static void setUpActions() {
		int[] palette = {ColorLib.rgb(200, 0, 0),ColorLib.rgb(0,  200, 0)}; 
		DataColorAction fill = new DataColorAction("blog.nodes","value", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
		int[] palette2 = {ColorLib.rgb(200, 0, 200), ColorLib.rgb(0,  200, 200), ColorLib.rgb(200,  200, 0), ColorLib.rgb(50,  50, 0)}; 
		DataColorAction ngo = new DataColorAction("blog.edges","Edgetype", Constants.NOMINAL, VisualItem.STROKECOLOR, palette2);
		ActionList color = new ActionList();
		color.add(fill);
		color.add(ngo);
		ActionList layout = new ActionList(Activity.INFINITY);		
		layout.add(new ForceDirectedLayout("blog", true));
		layout.add(new RepaintAction());
		vis.putAction("color", color);
		vis.putAction("layout", layout);
	}

}
