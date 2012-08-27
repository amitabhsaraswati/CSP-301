package bookviz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;

//This class is used to take input from gml files.
public class Parser {
	Graph pol;
	Graph blog;
	//This function is for the polbooks.gml file
	public Graph parsebook() throws IOException{
		/* We basically use filereader and bufferedreader
		 * to read our inputs, and use the StringTokenizer
		 * class to parse the given data.
		 */
		System.out.println("Enter the location of the file:");
		Scanner file = new Scanner(System.in);
		String file1 = file.nextLine();
		FileReader read = new FileReader(file1);
		BufferedReader reader = new BufferedReader(read);
		@SuppressWarnings("unused")
		String creator = reader.readLine();
		@SuppressWarnings("unused")
		String graph = reader.readLine();
		@SuppressWarnings("unused")
		String waste = reader.readLine();
		String directed = reader.readLine();
		StringTokenizer direction = new StringTokenizer(directed, "d ");
		direction.nextToken();
        int bool = Integer.parseInt(direction.nextToken());
        
        /* Now we create a graph with columns corresponding
         * to all the given datasets contained in a node.
         */
        
        if (bool == 1){
        	pol = new Graph(true);
        	pol.addColumn("id", int.class);
        	pol.addColumn("label", String.class);
        	pol.addColumn("value", String.class);
        	pol.addColumn("Edgetype", String.class);
        }
        else {
        	pol = new Graph(false);
        	pol.addColumn("id", int.class);
        	pol.addColumn("label", String.class);
        	pol.addColumn("value", String.class);
        	pol.addColumn("Edgetype", String.class);
        }
		while(true){							//We run the while loop until we reach the end of file.
			String line = reader.readLine();
			if (line==null){
				break;
			}

			//We store all the data for a given node.

			else if (line.equals("  node")){
				@SuppressWarnings("unused")
				String waste1 = reader.readLine();
				Node book = pol.addNode();
				String id = reader.readLine();
				StringTokenizer id1 = new StringTokenizer(id, "d ");
				id1.nextToken();
		        int no = Integer.parseInt(id1.nextToken());
		        book.set("id",no);
		        String label = reader.readLine();
		        label = label.replaceAll("label", "");
				//StringTokenizer label1 = new StringTokenizer(label, "-");
		        book.set("label", label);
				String value = reader.readLine();
				StringTokenizer value1 = new StringTokenizer(value, "e ");
				value1.nextToken();
				String s = value1.nextToken();
				book.set("value",s.substring(1, 2));
			}
			
			//We form edges between two given nodes.
			
			else if (line.equals("  edge")){
				@SuppressWarnings("unused")
				String waste2 = reader.readLine();
				String source = reader.readLine();
				StringTokenizer source1 = new StringTokenizer(source, "e ");
				source1.nextToken();
		        int src = Integer.parseInt(source1.nextToken());
		        String target = reader.readLine();
				StringTokenizer target1 = new StringTokenizer(target, "t ");
				target1.nextToken();
		        int tar = Integer.parseInt(target1.nextToken());
		        int apple = pol.addEdge(src, tar);
                Edge abc = pol.getEdge(apple);
                String mango = (String) (pol.getNode(src)).get("value");
                String banana = (String) (pol.getNode(tar)).get("value");
                abc.set("Edgetype", mango+banana );
                //System.out.println(mango+banana);
		        }
			else {
				continue;
			}
		}
		return pol;
	}
	//This function is for the polblogs.gml file.
		@SuppressWarnings("unused")
		public Graph parseblog() throws IOException{
			/* We basically use filereader and bufferedreader
			 * to read our inputs, and use the StringTokenizer
			 * class to parse the given data.
			 */
			System.out.println("Enter the location of the file:");
			Scanner file = new Scanner(System.in);
			String file1 = file.nextLine();
			FileReader read = new FileReader(file1);
			BufferedReader reader = new BufferedReader(read);
			String creator = reader.readLine();
			String graph = reader.readLine();
			String directed = reader.readLine();
			StringTokenizer direction = new StringTokenizer(directed, "d ");
			direction.nextToken();
	        int bool = Integer.parseInt(direction.nextToken());
	        
	        /* Now we create a graph with columns corresponding
	         * to all the given datasets contained in a node.
	         */
	        if (bool == 1){
	        	blog = new Graph(true);
	        	blog.addColumn("id", int.class);
	        	blog.addColumn("label", String.class);
	        	blog.addColumn("value", String.class);
	        	blog.addColumn("source", String.class);
	        	blog.addColumn("Edgetype", String.class);
	        	Node blogs = blog.addNode();
	        }
	        else {
	        	blog = new Graph(false);
	        	blog.addColumn("id", int.class);
	        	blog.addColumn("label", String.class);
	        	blog.addColumn("value", String.class);
	        	blog.addColumn("source", String.class);
	        	blog.addColumn("Edgetype", String.class);   
	        	Node blogs = blog.addNode();
	        }
			while(true){												//We run the while loop until we reach the end of file.
				String line = reader.readLine();
				if (line==null){
					break;
				}
				
				//We store all the data for a given node.
				else if (line.equals("  node [")){
					Node blogger = blog.addNode();
					String id = reader.readLine();
					StringTokenizer id1 = new StringTokenizer(id, "d ");
					id1.nextToken();
			        int no = Integer.parseInt(id1.nextToken());
			        blogger.set("id",no);
			        String label = reader.readLine();
			        label = label.replaceAll("label", "");
					blogger.set("label", label);				
					String value = reader.readLine();
					StringTokenizer value1 = new StringTokenizer(value, "e ");
					value1.nextToken();
					String val = value1.nextToken();
					blogger.set("value",val);
					String source = reader.readLine();
					StringTokenizer source1 = new StringTokenizer(source, "e ");
					source1.nextToken();
					blogger.set("source",source1.nextToken());
				}
				
				//We form edges between two given nodes.
				else if (line.equals("  edge [")){
					String source = reader.readLine();	
					StringTokenizer source1 = new StringTokenizer(source, "e ");
					source1.nextToken();
			        int src = Integer.parseInt(source1.nextToken());
			        String target = reader.readLine();
					StringTokenizer target1 = new StringTokenizer(target, "t ");
					target1.nextToken();
			        int tar = Integer.parseInt(target1.nextToken());
			        int apple = blog.addEdge(src, tar);
			        Edge abc = blog.getEdge(apple);
	                String mango = (String) (blog.getNode(src)).get("value");
	                String banana = (String) (blog.getNode(tar)).get("value");
	                abc.set("Edgetype", mango+banana );
				}
				else {
					continue;
				}
			}
			return blog;
		}
	}