package bookviz;

import java.util.Random;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;

/* This class is used to create a random graph equivalent to the one
 * given in the polbooks.gml file. We basically, create same number of
 * liberal, neutral and conservative nodes, and then, randomly form edges
 * between them. Total number of edges in the given graph, and the random
 * graph created here, is same.
 */


public class Random1 {
	Graph g;
	int p,q,r;
	Random rand = new Random();
	String[] s = new String [] {"l","n","c"};
	String[] t = new String [] {"l","n"};
	String[] u = new String [] {"n","c"};
	String[] v = new String [] {"l","c"};
	public Graph Randcreator(){
	g = new Graph(false);
	g.addColumn("id", int.class);
	g.addColumn("value", String.class);
	g.addColumn("Edgetype", String.class);
	
	//This loop is used to create the nodes of the random graph.
	//We ensure same number of same type of nodes as in the given data.
	for(int i=0;i<105;i++){
		Node cook =g.addNode();
		cook.set("id",i);
		if(p<43 && q<13 && r<49){
		int n = rand.nextInt(2);
		cook.set("value", s[n]);
		if(s[n]=="l"){p++;}
		else if(s[n]=="n"){q++;}
		else{r++;}
		}
		else if(p>42 && q<13 && r<49){
			int n = rand.nextInt(1);
			cook.set("value", u[n]);
			if(u[n]=="n"){q++;}
			else{r++;}
			}
		else if(p<43 && q>12 && r<49){
			int n = rand.nextInt(1);
			cook.set("value", v[n]);
			if(s[n]=="l"){p++;}
			else{r++;}
			}
		else if(p<43 && q<13 && r>48){
			int n = rand.nextInt(1);
			cook.set("value", t[n]);
			if(s[n]=="l"){p++;}
			else{q++;}
			}
		else if (p>42 && q>12){
			cook.set("value", "c");
			r++;
		}
		else if (r>48 && q>12){
			cook.set("value", "l");
			p++;
		}
		else{
			cook.set("value", "n");
			q++;
		}
	}
	
	//This for loop creates edges between all the nodes of the random graph.
	for(int j=0;j<441;j++){
		int x = rand.nextInt(105);
		int y = rand.nextInt(105);
		int apple = g.addEdge(x, y);
		Edge abc = g.getEdge(apple);
        String mango = (String) (g.getNode(x)).get("value");
        String banana = (String) (g.getNode(y)).get("value");
        abc.set("Edgetype", mango+banana );
	}
	return g;
	
	}
}
