package politics;

import java.io.*;

import java.util.*;

import prefuse.data.Graph;

import prefuse.data.Node;



/*

 * This is the code fragment which was used to compute all the stats used to create bar graphs,

 * used in our first app. We basically have an iterator over all the states, and it helps us compute

 * total number of some particular type of MPs in all the states. Right now, the code is modified to

 * compute MPs belonging to various parties in the 14th Lok Sabha. It can be used to find out all other

 * stats with very slight modifications.

 * */



public class Parser {

	Graph g;

	public Graph MP() throws IOException{

		FileReader read = new FileReader("C:\\Users\\Varunjay\\Desktop\\MPTrack-14.txt");

		BufferedReader reader = new BufferedReader(read);

		reader.readLine();

		g = new Graph(false);

		g.addColumn("Name", String.class);

		g.addColumn("State", String.class);

		g.addColumn("Political Party", String.class);

		g.addColumn("Attendance", String.class);

		while(true){

			String line = reader.readLine();

			if (line == null){

				break;

			}

			else {

				StringTokenizer st = new StringTokenizer(line, "	");

				Node m = g.addNode();

				m.set("Name", st.nextToken());

				m.set("State", st.nextToken());

				m.set("Political Party", st.nextToken());

				m.set("Attendance", st.nextToken());

			}

		}

		return g;

	}

	

	public void Party(Graph g){

		int z = g.getNodeCount();

		int a = 0;

		int b = 0;

		int c = 0;

		int d = 0;

		int e = 0;

		int f = 0;

		int r = 0;

		int t = 0;

		int u = 0;

		int pr= 0;

		int pv = 0;

		int pm = 0;

		int vv = 0;

		int v = 0;

		String[] states = new String[] {"Uttar Pradesh","Maharashtra", "Andhra Pradesh", "West Bengal", "Bihar", "Tamil Nadu", "Madhya Pradesh", "Karnataka", "Gujarat", "Rajasthan", "Orissa", "Kerala", "Assam", "Jharkhand", "Punjab", "Chhattisgarh", "Haryana", "Delhi", "Jammu and Kashmir", "Uttarakhand", "Himachal Pradesh", "Arunachal Pradesh", "Goa", "Manipur", "Meghalaya", "Tripura", "Mizoram", "Nagaland", "Sikkim", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu", "Lakshadweep", "Pondicherry"};

		for (int loop = 0; loop<states.length; loop++){

		for (int y = 0; y<z; y++){

			Node n = g.getNode(y);

			String s = (String)n.get("State");

			if (s.equals(states[loop])){

				String p = (String)n.get("Political Party");

				if (p.equals("INC")){

					a++;

				}

				else if (p.equals("BJP")){

					b++;

				}

				else if (p.equals("CPI")||p.equals("CPI(M)")){

					c++;

				}

/*				if (p.equals("0%")||p.equals("1%")||p.equals("2%")||p.equals("3%")||p.equals("4%")||p.equals("5%")||p.equals("6%")||p.equals("7%")||p.equals("8%")||p.equals("9%")||p.equals("10%")){

					a++;

				}

				else if (p.equals("11%")||p.equals("12%")||p.equals("13%")||p.equals("14%")||p.equals("15%")||p.equals("16%")||p.equals("17%")||p.equals("18%")||p.equals("19%")||p.equals("20%")){

					b++;

				}

				else if (p.equals("21%")||p.equals("22%")||p.equals("23%")||p.equals("24%")||p.equals("25%")||p.equals("26%")||p.equals("27%")||p.equals("28%")||p.equals("29%")||p.equals("30%")){

					c++;

				}

				else if (p.equals("31%")||p.equals("32%")||p.equals("33%")||p.equals("34%")||p.equals("35%")||p.equals("36%")||p.equals("37%")||p.equals("38%")||p.equals("39%")||p.equals("40%")){

					d++;

				}

				else if (p.equals("41%")||p.equals("42%")||p.equals("43%")||p.equals("44%")||p.equals("45%")||p.equals("46%")||p.equals("47%")||p.equals("48%")||p.equals("49%")||p.equals("50%")){

					e++;

				}

				else if (p.equals("51%")||p.equals("52%")||p.equals("53%")||p.equals("54%")||p.equals("55%")||p.equals("56%")||p.equals("57%")||p.equals("58%")||p.equals("59%")||p.equals("60%")){

					f++;

				}

				else if (p.equals("61%")||p.equals("62%")||p.equals("63%")||p.equals("64%")||p.equals("65%")||p.equals("66%")||p.equals("67%")||p.equals("68%")||p.equals("69%")||p.equals("70%")){

					r++;

				}

				else if (p.equals("71%")||p.equals("72%")||p.equals("73%")||p.equals("74%")||p.equals("75%")||p.equals("76%")||p.equals("77%")||p.equals("78%")||p.equals("79%")||p.equals("80%")){

					t++;

				}

				else if (p.equals("81%")||p.equals("82%")||p.equals("83%")||p.equals("84%")||p.equals("85%")||p.equals("86%")||p.equals("87%")||p.equals("88%")||p.equals("89%")||p.equals("90%")){

					u++;

				}

				else if (p.equals("91%")||p.equals("92%")||p.equals("93%")||p.equals("94%")||p.equals("95%")||p.equals("96%")||p.equals("97%")||p.equals("98%")||p.equals("99%")||p.equals("100%")){

					pr++;

				}*/

				else v++;

				}

			}

		

		System.out.println(a+" "+b+" "+c+" "+v);

		a = 0;

		b = 0;

		c = 0;

		d = 0;

		e = 0;

		f = 0;

		r = 0;

		t = 0;

		u = 0;

		pr= 0;

		pv = 0;

		pm = 0;

		vv = 0;

		v = 0;

		}

	}

	

	public static void main(String[] args) throws IOException{

		Parser a = new Parser();

		Graph s = a.MP();

		a.Party(s);

	}

}