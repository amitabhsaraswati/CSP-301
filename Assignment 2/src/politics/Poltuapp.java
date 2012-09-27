package politics;



import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



import javax.swing.Box;

import javax.swing.BoxLayout;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JToolBar;



import prefuse.Visualization;

import prefuse.action.ActionList;

import prefuse.action.assignment.ColorAction;

import prefuse.action.assignment.DataShapeAction;

import prefuse.action.layout.AxisLabelLayout;

import prefuse.action.layout.AxisLayout;

import prefuse.data.Table;

import prefuse.data.io.DelimitedTextTableReader;

import politics.Poltu;

import prefuse.util.ColorLib;

import prefuse.util.ui.JPrefuseApplet;

import prefuse.visual.VisualItem;



public class Poltuapp extends JPrefuseApplet {



    public void init() {

    	

         String xfield = "State";

         String yfield = "Political party";

         String sfield = "Educational qualifications";

         String data = "/Book1.txt";

         Table table = null;

         try {

             table = new DelimitedTextTableReader().readTable(data);

         } catch ( Exception e ) {

             e.printStackTrace();

             System.exit(1);

         }

          Poltu sp = politics.Poltu.demo(data, xfield, yfield, sfield);

          JToolBar toolbar = politics.Poltu.getEncodingToolbar(sp, xfield, yfield, sfield);



          this.getContentPane().add(toolbar, BorderLayout.SOUTH);

          this.getContentPane().add(sp, BorderLayout.CENTER);

          this.setVisible(true);    

    }

    

    

    

    }

