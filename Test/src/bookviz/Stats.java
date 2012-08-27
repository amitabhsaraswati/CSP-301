package bookviz;

import java.util.Iterator;

import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;

public class Stats {
	
	public double edgeratio(Graph g){
		//this function computes the no of edges between same kind of nodes
		//and also returns the ratio of the no of such edges to the total no of edges for the first kind of data
		int z = g.getEdgeCount();
		int x = 0;
		for (int k = 0; k<z; k++){
			if (g.getEdge(k).get("Edgetype").equals("ll")||g.getEdge(k).get("Edgetype").equals("nn")||g.getEdge(k).get("Edgetype").equals("cc")){
				x++;
			}
			else continue;
		}
		System.out.println(x);
		return x/z;
	}
	
	public void edgerat(Graph g){
		//this function computes the no of edges between same kind of nodes
		//and also returns the ratio of the no of such edges to the total no of edges for the second kind of data
		int z = g.getEdgeCount();
		int x = 0;
		for (int k = 0; k<z; k++){
			if (g.getEdge(k).get("Edgetype").equals("11")||g.getEdge(k).get("Edgetype").equals("00")){
				x++;
			}
			else continue;
		}
		System.out.println(x);
	}
	
	public float triads(Graph g){
		//this function computes the no of 3 cycles in the graph to the ratio of the no of 3 connected nodes in graph.
		//this is basically the clustering coefficient
		float p = 0;
		float q = 0;
		for(int i=0;i<g.getNodeCount();i++){
			Node n = g.getNode(i);
			Iterator<Node> a = n.neighbors();
			while (a.hasNext()){
				Node c =a.next();
				Iterator<Node> b = n.neighbors();
				while(b.hasNext()){
					if (g.getEdge(c, b.next()) == null){
					p+=2;
				}
				else {
					p+=3;
					q+=3;
				}			
			}
		}
		}
		float z = 1000*q/p;
		System.out.println(z);
		return q/p;
	}
	
	public void Degree(Graph g){
		//this function computes the degree of each and every edge in the graph
		for (int i = 0; i<g.getNodeCount(); i++){
			Node n = g.getNode(i);
			System.out.println(n.getDegree());
		}
	}
}