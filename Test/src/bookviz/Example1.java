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
import prefuse.visual.VisualItem;

public class Example1 {
	private static Graph graph;
	private static Visualization vis;
	private static Display d;
	public static void main(String [] argv) throws IOException{
		Parser a = new Parser();
		graph = a.parsebook();
		System.out.println(graph.getNodeCount());
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
		d.setBackground(Color.BLACK);
	}




	private static void setUpActions() {
		int[] palette = {ColorLib.rgb(200, 0, 0), ColorLib.rgb(200,200, 200), ColorLib.rgb(0,  200, 0)}; 
		DataColorAction fill = new DataColorAction("pol.nodes","value", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
		ColorAction edges = new ColorAction("pol.edges",VisualItem.STROKECOLOR,ColorLib.rgb(0, 0, 200));
		ActionList color = new ActionList();
		color.add(fill);
		color.add(edges);
		ActionList layout = new ActionList(Activity.INFINITY);		
		layout.add(new ForceDirectedLayout("pol", true));
		layout.add(new RepaintAction());
		vis.putAction("color", color);
		vis.putAction("layout", layout);
	}


	private static void setUPRenderers() {
		ShapeRenderer r = new ShapeRenderer();
		vis.setRendererFactory(new DefaultRendererFactory(r));
	}


	private static void setUpVisualization() {
		vis = new Visualization();
		vis.add("pol", graph);
	}
}
