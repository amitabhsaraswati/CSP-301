package bookviz;

import java.util.Iterator;

import prefuse.data.Graph;
import prefuse.data.Node;

public class Stats {
	
	public double edgeratio(Graph g){
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
	
	public double triads(Graph g){
		int p = 0;
		int q = 0;
		for(int i=0;i<g.getNodeCount();i++){
			Node n = g.getNode(i);
			Iterator<Node> a = n.neighbors();
			while (a.hasNext()){
				Node c =a.next();
				Iterator<Node> b = n.neighbors();
				while(b.hasNext()){
					if (g.getEdge(c, b.next()) == null){
					p++;
				}
				else {
					p++;
					q++;
				}			
			}
		}
		}
		System.out.println(p+" "+q);
		return q/p;
	}
	
	
	
}
