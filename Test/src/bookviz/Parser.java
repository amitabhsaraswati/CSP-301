package bookviz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import prefuse.data.Graph;
import prefuse.data.Node;

public class Parser {
	Graph pol;
	Graph blog;
	public Graph parsebook() throws IOException{
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
        if (bool == 1){
        	pol = new Graph(true);
        	pol.addColumn("id", int.class);
        	pol.addColumn("label", String.class);
        	pol.addColumn("value", String.class);
        }
        else {
        	pol = new Graph(false);
        	pol.addColumn("id", int.class);
        	pol.addColumn("label", String.class);
        	pol.addColumn("value", String.class);
        }
		while(true){
			String line = reader.readLine();
			if (line==null){
				break;
			}
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
				StringTokenizer label1 = new StringTokenizer(label, "l ");
				label1.nextToken();
				book.set("label", label1.nextToken());
				String value = reader.readLine();
				StringTokenizer value1 = new StringTokenizer(value, "e ");
				value1.nextToken();
				book.set("value",value1.nextToken());
			}
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
		        pol.addEdge(src, tar);
			}
			else {
				continue;
			}
		}
		
		return pol;
	}
	
	public void parseblog() throws IOException{
		System.out.println("Enter the location of the file:");
		Scanner file = new Scanner(System.in);
		String file1 = file.nextLine();
		FileReader read = new FileReader(file1);
		BufferedReader reader = new BufferedReader(read);
		@SuppressWarnings("unused")
		String creator = reader.readLine();
		@SuppressWarnings("unused")
		String graph = reader.readLine();
		String directed = reader.readLine();
		StringTokenizer direction = new StringTokenizer(directed, "d ");
		direction.nextToken();
        int bool = Integer.parseInt(direction.nextToken());
        if (bool == 1){
        	Graph blog = new Graph(true);
        	blog.addColumn("id", int.class);
        	blog.addColumn("label", String.class);
        	blog.addColumn("value", int.class);
        	blog.addColumn("source", String.class);
        }
        else {
        	Graph blog = new Graph(false);
        	blog.addColumn("id", int.class);
        	blog.addColumn("label", String.class);
        	blog.addColumn("value", int.class);
        	blog.addColumn("source", String.class);
        }
		while(true){
			String line = reader.readLine();
			if (line==null){
				break;
			}
			else if (line.equals("  node [")){
				Node blogs = pol.addNode();
				String id = reader.readLine();
				StringTokenizer id1 = new StringTokenizer(id, "d ");
				id1.nextToken();
		        int no = Integer.parseInt(id1.nextToken());
		        blogs.set("id",no);
		        String label = reader.readLine();
				StringTokenizer label1 = new StringTokenizer(label, "l ");
				label1.nextToken();
				blogs.set("label", label1.nextToken());
				String value = reader.readLine();
				StringTokenizer value1 = new StringTokenizer(value, "e ");
				id1.nextToken();
				int no1 = Integer.parseInt(value1.nextToken());
				blogs.set("value",no1);
				String source = reader.readLine();
				StringTokenizer source1 = new StringTokenizer(source, "e ");
				source1.nextToken();
				blogs.set("source",source1.nextToken());
			}
			else if (line.equals("  edge [")){
				String source = reader.readLine();
				StringTokenizer source1 = new StringTokenizer(source, "e ");
				source1.nextToken();
		        int src = Integer.parseInt(source1.nextToken());
		        String target = reader.readLine();
				StringTokenizer target1 = new StringTokenizer(target, "t ");
				target1.nextToken();
		        int tar = Integer.parseInt(target1.nextToken());
		        blog.addEdge(src, tar);
			}
			else {
				continue;
			}
		}
	}
}
//retest