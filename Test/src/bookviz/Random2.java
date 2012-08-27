package bookviz;

import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;

import java.util.Random;

/* This class is used to create a random graph equivalent to the one
 * given in the polblogs.gml file. We basically, create same number of
 * 0 and 1 nodes, and then, randomly form edges between them. Total
 * number of edges in the given graph, and the random graph created
 * here, is same.
 */

public class Random2 {
	Graph g;
	Random rand = new Random();
	String [] lol = {"1" , "0"};
	
	public Graph randCreator(){
		int p = 0,q = 0;
		g = new Graph(true);
		g.addColumn("id", int.class);
		g.addColumn("value", String.class);
    	g.addColumn("source", String.class);
    	g.addColumn("Edgetype", String.class);
    	
    	//This loop is used to create the nodes of the random graph.
    	//We ensure same number of same type of nodes as in the given data.
		for(int i =0;i<1490;i++){
			Node hook = g.addNode();
			hook.set("id", i);
			if(p<732 && q <758){
				int n = rand.nextInt(1);
				hook.set("value",lol[n]);
				if(n == 0){
					p++;
				}
				else q++;
			}
			else if(p>731){
				hook.set("value", "0");
			}
			else{
				hook.set("value", "1");
			}
		}
		
		//This for loop creates edges between all the nodes of the random graph.
    	for(int j=0;j<19090;j++){
			int x = rand.nextInt(1490);
			int y = rand.nextInt(1490);
			int apple = g.addEdge(x, y);
			Edge abc = g.getEdge(apple);
	        String mango = (String) (g.getNode(x)).get("value");
	        String banana = (String) (g.getNode(y)).get("value");
	        abc.set("Edgetype", mango+banana );
	        abc.set("source", x);
		}
		return g;
	}
}
