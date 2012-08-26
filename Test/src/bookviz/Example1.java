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
import prefuse.controls.NeighborHighlightControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;

public class Example1 {
	private static Graph graph;
	private static Visualization vis;
	private static Display d;
	public static void main(String [] argv) throws IOException{
		Parser a = new Parser();
		graph = a.parsebook();
		Stats se = new Stats();
		se.edgeratio(graph);
		setUpVisualization();
		setUPRenderers();
		setUpActions();
		setUpDisplay();
		JFrame frame = new JFrame("Book Visualization");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(d);
		frame.pack();
		frame.setVisible(true);
		vis.run("color");
		vis.run("layout");
		vis.run("layout1");
		}

//sets up the display, which is used to display the visualization 
	private static void setUpDisplay() {
		d = new Display(vis);
		d.setSize(720, 500);
		d.addControlListener(new DragControl());
		d.addControlListener(new PanControl());
		d.addControlListener(new ZoomControl());
		d.addControlListener(new Hover());
        d.addControlListener(new WheelZoomControl());
        d.addControlListener(new ZoomToFitControl());
        d.addControlListener(new Highlight());
		d.setBackground(Color.BLACK);
	}



/*sets up the actions which are to be carried out in the visualization such as displaying the nodes of the
 * graph "pol", colouring nodes of different types differently(3 types) and edges of different types differently 
 * Legend for Node Colours:
 * Red - Conservative
 * Blue - Leftist
 * Green - Neutral
 * Legend for Edge Colours:
 * Red - Conservative to Conservative
 * Blue - Leftist to Leftist
 * Green - Neutral to Neutral
 * Yellow - Conservative to Leftist or vice versa
 * White - Between Neutral and Conservative/Leftist 
	
	*/private static void setUpActions() {
		int[] palette = {ColorLib.rgb(200, 0, 0), ColorLib.rgb(0,0, 200), ColorLib.rgb(0,  200, 0)}; 
		DataColorAction fill = new DataColorAction("pol.nodes","value", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
        int[] palette2 = {ColorLib.rgb(200, 0, 0), ColorLib.rgb(200,200, 0), ColorLib.rgb(200,  200, 200), ColorLib.rgb(200,  200, 0), ColorLib.rgb(0,  0, 200), ColorLib.rgb(200,  200, 200), ColorLib.rgb(200,  200, 200), ColorLib.rgb(200,  200, 200), ColorLib.rgb(0, 200, 0)}; 
		DataColorAction ngo = new DataColorAction("pol.edges","Edgetype", Constants.NOMINAL, VisualItem.STROKECOLOR, palette2);
		ColorAction fill1 = new ColorAction("pol.nodes",VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0));
		ActionList color = new ActionList();
		color.add(fill);
		color.add(fill1);
		color.add(ngo);
		ActionList layout1 = new ActionList();
		layout1.add(new RandomLayout("pol.nodes"));
		ActionList layout = new ActionList(Activity.INFINITY, Activity.DEFAULT_STEP_TIME);		
		layout.add(new ForceDirectedLayout("pol", false));
		layout.add(new RepaintAction());
		vis.putAction("color", color);
		vis.putAction("layout", layout);
		vis.putAction("layout1", layout1);
	}

//sets up the Renderers
	private static void setUPRenderers() {
		RenderersCustom r = new RenderersCustom();
		DefaultRendererFactory abcd = new DefaultRendererFactory(r);
		vis.setRendererFactory(abcd);	
	}

//sets up the visualization to which the graph "pol" is added
	private static void setUpVisualization() {
		vis = new Visualization();
		vis.add("pol", graph);
	}
}
