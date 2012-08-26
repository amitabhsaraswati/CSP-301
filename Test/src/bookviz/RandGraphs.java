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
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;

public class RandGraphs {
	private static Graph graph;
	private static Visualization vis;
	private static Display d;
	public static void main(String [] argv) throws IOException{
		for (int i = 0; i<50; i++){
			//creates 50 random graphs with same number of nodes of each type as in polbooks.gml
			Random1 a = new Random1();
			graph = a.Randcreator();
		//computes the ratio of sum of no. of edges of same type to the total no. of edges i.e, (ll + cc + nn)/total edges 
			Stats se = new Stats();
			se.edgeratio(graph);
		}
		//System.out.println(graph.getEdgeCount());
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

	//sets up the display, which is used to display the visualization 
	private static void setUpDisplay() {
		d = new Display(vis);
		d.setSize(720, 500);
		d.addControlListener(new DragControl());
		d.addControlListener(new PanControl());
		d.addControlListener(new ZoomControl());
	    d.addControlListener(new WheelZoomControl());
        d.addControlListener(new ZoomToFitControl());
		d.setBackground(Color.BLACK);
	}


	/*sets up the actions which are to be carried out in the visualization such as displaying the nodes of the
	 * graph "pol", colouring nodes of different types differently(3 types) and edges of different types differently
	 *  * Legend for Node Colours:
 * Red - Conservative
 * Blue - Liberal
 * Green - Neutral
 * Legend for Edge Colours:
 * Red - Conservative to Conservative
 * Blue - Liberal to Liberal
 * Green - Neutral to Neutral
 * Yellow - Conservative to Liberal or vice versa
 * White - Between Neutral and Conservative/Liberal
	
	*/ 

	private static void setUpActions() {
		int[] palette = {ColorLib.rgb(200, 0, 0), ColorLib.rgb(0,0, 200), ColorLib.rgb(0,  200, 0)}; 
		DataColorAction fill = new DataColorAction("pol.nodes","value", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
		int[] palette2 = {ColorLib.rgb(200, 0, 0), ColorLib.rgb(200,200, 0), ColorLib.rgb(200,  200, 200), ColorLib.rgb(200,  200, 0), ColorLib.rgb(0,  0, 200), ColorLib.rgb(200,  200, 200), ColorLib.rgb(200,  200, 200), ColorLib.rgb(200,  200, 200), ColorLib.rgb(0, 200, 0)}; 
		DataColorAction ngo = new DataColorAction("pol.edges","Edgetype", Constants.NOMINAL, VisualItem.STROKECOLOR, palette2);
		ActionList color = new ActionList();
		color.add(fill);
		color.add(ngo);
		ActionList layout = new ActionList(Activity.INFINITY);		
		layout.add(new ForceDirectedLayout("pol", false));
		layout.add(new RepaintAction());
		vis.putAction("color", color);
		vis.putAction("layout", layout);
	}

	//sets up the Renderers for the visualization
	private static void setUPRenderers() {
		ShapeRenderer r = new ShapeRenderer();
		vis.setRendererFactory(new DefaultRendererFactory(r));
	}

	//sets up the visualization to which the graph "pol" is added
	private static void setUpVisualization() {
		vis = new Visualization();
		vis.add("pol", graph);
	}
}
