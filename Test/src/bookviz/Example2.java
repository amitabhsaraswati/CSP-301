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
import prefuse.controls.ZoomControl;
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
	private static Graph InOut(){
		graph.addColumn("Incoming", int.class);
		graph.addColumn("Outgoing", int.class);
		graph.addColumn("Total", int.class);
		graph.addColumn("Samein", int.class);
		graph.addColumn("Sameout", int.class);
		graph.addColumn("Diffin", int.class);
		graph.addColumn("Diffout", int.class);
		int p = 0,q = 0 ,r = 0;
		for(int i = 0;i<graph.getNodeCount();i++){
			Node n = graph.getNode(i);
			Iterator w = n.inEdges();
			while(w.hasNext()){
				w.next();
				p++;
			}
			n.set("Incoming", p);
			p = 0;
		}
		int low =0;
		for(int j=0;j<graph.getNodeCount();j++){
			Node n = graph.getNode(j);
			Iterator a = n.inEdges();
			while(a.hasNext()){
				if(((Tuple) a.next()).get("Edgetype").equals("00")||((Tuple) a.next()).get("Edgetype").equals("11")){
					low++;
				}
			}
			n.set("Samein",low);
		}
		
		int cow =0;
		for(int j=0;j<graph.getNodeCount();j++){
			Node n = graph.getNode(j);
			Iterator a = n.inEdges();
			while(a.hasNext()){
				if(((Tuple) a.next()).get("Edgetype").equals("01")||((Tuple) a.next()).get("Edgetype").equals("10")){
					cow++;
				}
			}
			n.set("Diffin",cow);
		}
		
		int how =0;
		for(int j=0;j<graph.getNodeCount();j++){
			Node n = graph.getNode(j);
			Iterator a = n.outEdges();
			while(a.hasNext()){
				if(((Tuple) a.next()).get("Edgetype").equals("01")||((Tuple) a.next()).get("Edgetype").equals("10")){
					how++;
				}
			}
			n.set("Diffout",how);
		}
		
		int wow =0;
		for(int j=0;j<graph.getNodeCount();j++){
			Node n = graph.getNode(j);
			Iterator a = n.outEdges();
			while(a.hasNext()){
				if(((Tuple) a.next()).get("Edgetype").equals("00")||((Tuple) a.next()).get("Edgetype").equals("11")){
					wow++;
				}
			}
			n.set("Sameout",wow);
		}
			for(int j = 0;j<graph.getNodeCount();j++){
				Node m = graph.getNode(j);
				Iterator x = m.outEdges();
				while(x.hasNext()){
					x.next();
					q++;
				}
				m.set("Outgoing", q);
				q = 0;
			}
			for(int k = 0; k<graph.getNodeCount();k++){
				Node o = graph.getNode(k);
			    r = (Integer)o.get("Incoming")+(Integer)o.get("Outgoing");
			    o.set("Total", r);
			}
			return graph;
	}
	}


