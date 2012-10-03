package politics;

import java.applet.Applet;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.IOException;



import javax.swing.Box;

import javax.swing.BoxLayout;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JToolBar;



import prefuse.Display;

import prefuse.Visualization;

import prefuse.action.ActionList;

import prefuse.controls.DragControl;

import prefuse.controls.PanControl;

import prefuse.controls.WheelZoomControl;

import prefuse.controls.ZoomControl;

import prefuse.controls.ZoomToFitControl;

import prefuse.data.Table;

import prefuse.util.ui.JPrefuseApplet;



public class Graphs extends JPrefuseApplet {

	

	private static Visualization vis;

	private static Display d;

	static String a = "Age"; //a is a global variable of String class with initial value "Age" 
	
	static String b = "Absolute"; //b is a global variable of String class with initial value "Absolute"

	static String c = "14"; //c is a global variable of String class with initial value "14"
	
	private static JPrefuseApplet frame; 

	

	private static void setUpDisplay() {

		d = new Display(vis);

		d.setSize(720, 500);

		d.addControlListener(new DragControl()); //enables dragging of nodes

		d.addControlListener(new PanControl()); // enables panning

		d.addControlListener(new ZoomControl()); // enables zooming in and out

        d.addControlListener(new WheelZoomControl()); // enables using the mouse wheel for zooming in and out 

        d.addControlListener(new ZoomToFitControl()); 

        

	}

	

	private static void setUpVisualization() { // sets up the visualization

		vis = new Visualization();

	}

	 

    public static JToolBar xyz() { //creates a toolbar at the bottom of the Java Frame

        JToolBar toolbar = new JToolBar();



     toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS)); // toolbar is created along the x axis

     toolbar.add(Box.createHorizontalStrut(15));

	

     String[] irule = {"Age", "Party", "Education", "Attendance"}; // string array for the drop down menu where data type is chosen

     String[] abs = {"Absolute", "Percent"}; // string array for the drop down menu where the graph type is chosen
     
     String[] sabha = {"14" , "15"} ; // string array for the drop down menu where the data set is chosen

	final JComboBox xcb = new JComboBox(irule); // creates a drop down menu for choosing the data type and displaying the corresponding graph

    xcb.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {

        	a = (String)xcb.getSelectedItem();

        	d.setBackgroundImage("C:/Amitabh/Academics/Sem 3/CSP 301/Assignment 2/" + a + b + c +".png" , false, false);
        	// concatenates strings a, b and c to obtain the name of the graph that needs to be displayed

        	frame.add(d);

        	frame.repaint();

        	;}
    });
    
    toolbar.add(new JLabel("Graph: ")); // the String "Graph: " is displayed before this drop down menu

    toolbar.add(xcb);

    toolbar.add(Box.createHorizontalStrut(2*15));
    

	final JComboBox ycb = new JComboBox(abs); // creates a drop down menu for choosing the graph type and displaying the corresponding graph

    ycb.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {

        	b = (String)ycb.getSelectedItem();

        	d.setBackgroundImage("C:/Amitabh/Academics/Sem 3/CSP 301/Assignment 2/" + a+ b+ c+".png" , false, false);
        	// concatenates strings a, b and c to obtain the name of the graph that needs to be displayed
        	frame.add(d);

        	frame.repaint();

        	;}
    });

    toolbar.add(new JLabel("Type: ")); // the String "Type: " is displayed before this drop down menu

    toolbar.add(ycb);

    toolbar.add(Box.createHorizontalStrut(2*15));
    

	final JComboBox zcb = new JComboBox(sabha); // creates a drop down menu for choosing the data set and displaying the corresponding graph

    zcb.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {

        	c = (String)zcb.getSelectedItem();

        	d.setBackgroundImage("C:/Amitabh/Academics/Sem 3/CSP 301/Assignment 2/" + a+ b+ c+ ".png" , false, false);
        	// concatenates strings a, b and c to obtain the name of the graph that needs to be displayed
        	frame.add(d);

        	frame.repaint();

        	;}
    });

    toolbar.add(new JLabel("LokSabha: ")); // the String "LokSabha: " is displayed before this drop down menu

    toolbar.add(zcb);

    toolbar.add(Box.createHorizontalStrut(2*15));
    

    return toolbar; //gives back the full toolbar along with all the created drop down menus



}

 public void init() { // this initializes the visualization

	 setUpVisualization();

		setUpDisplay();

		xyz();

		JToolBar toolbar = xyz();

        this.getContentPane().add(toolbar, BorderLayout.SOUTH); //the toolbar comes at the bottom of the Java Frame

        this.getContentPane().add(d, BorderLayout.CENTER); //the display comes at the center of the Java Frame

		this.setVisible(true); // the Java Frame's visibility is set as true

		frame = this;

	}

	

}