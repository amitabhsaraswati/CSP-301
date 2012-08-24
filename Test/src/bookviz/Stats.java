package bookviz;

import prefuse.data.Graph;

public class Stats {
	
	public double edgeratio(Graph g){
		int z = g.getEdgeCount();
		int x = 0;
		for (int k = 0; k<z; k++){
			if (g.getEdge(k).get("Edgetype").equals("ll")||g.getEdge(k).get("Edgetype").equals("nn")||g.getEdge(k).get("Edgetype").equals("cc")){
				x++;
			}
			else System.out.println(g.getEdge(k).get("Edgetype"));
		}
		System.out.println(x+":"+z);
		return x/z;
	}
	
	public double triads(Graph g){
		return 1.0;
	}
	
	
	
}
