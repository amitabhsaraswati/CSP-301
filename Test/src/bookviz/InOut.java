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

public class InOut {
	int p,q,r = 0;
	public void inout(Graph g){
		g.addColumn("Incoming", int.class);
		g.addColumn("Outgoing", int.class);
		g.addColumn("Total", int.class);
		g.addColumn("Samein", int.class);
		g.addColumn("Sameout", int.class);
		g.addColumn("Diffin", int.class);
		g.addColumn("Diffout", int.class);
		for(int i = 0;i<g.getNodeCount();i++){
			Node n = g.getNode(i);
			Iterator w = n.inEdges();
			while(w.hasNext()){
				w.next();
				p++;
			}
			n.set("Incoming", p);
			p = 0;
		}
		int low =0;
		for(int j=0;j<g.getNodeCount();j++){
			Node n = g.getNode(j);
			Iterator a = n.inEdges();
			while(a.hasNext()){
				EdgeItem b = (EdgeItem) a.next();
				if(b.get("Edgetype").equals("00")||b.get("Edgetype").equals("11")){
					low++;
				}
			}
			n.set("Samein",low);
		}
		
		int cow =0;
		
		for(int j=0;j<g.getNodeCount();j++){
			Node n = g.getNode(j);
			Iterator a = n.inEdges();
			while(a.hasNext()){
				EdgeItem b = (EdgeItem) a.next();
				if(b.get("Edgetype").equals("01")||b.get("Edgetype").equals("10")){
					cow++;
				}
			}
			n.set("Diffin",cow);
		}
		
		int how =0;
		for(int j=0;j<g.getNodeCount();j++){
			Node n = g.getNode(j);
			Iterator a = n.outEdges();
			while(a.hasNext()){
				EdgeItem b = (EdgeItem) a.next();
				if(b.get("Edgetype").equals("01")||b.get("Edgetype").equals("10")){
					how++;
				}
			}
			n.set("Diffout",how);
		}
		
		int wow =0;
		for(int j=0;j<g.getNodeCount();j++){
			Node n = g.getNode(j);
			Iterator a = n.outEdges();
			while(a.hasNext()){
				EdgeItem b = (EdgeItem) a.next();
				if(b.get("Edgetype").equals("00")||b.get("Edgetype").equals("11")){
					wow++;
				}
			}
			n.set("Sameout",wow);
		}
			for(int j = 0;j<g.getNodeCount();j++){
				Node m = g.getNode(j);
				Iterator x = m.outEdges();
				while(x.hasNext()){
					x.next();
					q++;
				}
				m.set("Outgoing", q);
				q = 0;
			}
			for(int k = 0; k<g.getNodeCount();k++){
				Node o = g.getNode(k);
			    r = (Integer)o.get("Incoming")+(Integer)o.get("Outgoing");
			    o.set("Total", r);
			}
	}
}
