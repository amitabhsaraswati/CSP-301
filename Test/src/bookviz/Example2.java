package bookviz;

import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFrame;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.CompositeAction;
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
import prefuse.data.Node;
import prefuse.data.Tuple;
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
    	graph = a.parseblog();//creates a graph from the input gml file using the parseblog() function of Parser
    	setUpVisualization();
		setUPRenderers();
		setUpActions();
		setUpDisplay();
		JFrame frame = new JFrame("Blog Visualization");
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
       	d.setBackground(Color.BLACK);
	}
    
  //sets up the Renderers for the visualization
    private static void setUPRenderers() {
    	RenderersCustom r = new RenderersCustom();
		DefaultRendererFactory abcd = new DefaultRendererFactory(r);
		vis.setRendererFactory(abcd);	
	}

  //sets up the visualization to which the graph "blog" is added
	private static void setUpVisualization() {
		vis = new Visualization();
		vis.add("blog", graph);
	}
	
	/*sets up the actions which are to be carried out in the visualization such as displaying the nodes of the
	 * graph "pol", colouring nodes of different types differently(2 types) and edges of different types differently 
		Legend for Node Colours:
		Red - Left Wing
		Blue - Right Wing
		Legend for Edge colours:
		 Red - Left to Left
		 Blue - Right to Right
		 Cyan - Left to Right
		 Yellow - Right to Left */
	private static void setUpActions() {
		int[] palette = {ColorLib.rgb(200, 200, 200),ColorLib.rgb(200,  0, 0), ColorLib.rgb(0, 0, 200)}; 
		DataColorAction fill = new DataColorAction("blog.nodes","value", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
		int[] palette2 = {ColorLib.rgb(200, 0, 0), ColorLib.rgb(0,  200, 200), ColorLib.rgb(200,  200, 0), ColorLib.rgb(0,  0, 200)}; 
		DataColorAction ngo = new DataColorAction("blog.edges","Edgetype", Constants.NOMINAL, VisualItem.STROKECOLOR, palette2);
		ColorAction fill1 = new ColorAction("blog.nodes",VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,0));
		ActionList color = new ActionList();
		color.add(fill);
		color.add(fill1);
		color.add(ngo);
		ActionList layout1 = new ActionList();
		layout1.add(new RandomLayout("blog.nodes"));
		ActionList layout = new ActionList(Activity.INFINITY);		
		layout.add(new ForceDirectedLayout("blog", false));
		layout.add(new RepaintAction());
		vis.putAction("color", color);
		vis.putAction("layout", layout);
		vis.putAction("layout1", layout1);
	
	}
		}


