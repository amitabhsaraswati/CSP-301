package politics;



import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.Insets;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;



import javax.swing.BorderFactory;

import javax.swing.Box;

import javax.swing.BoxLayout;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JToolBar;

import javax.swing.JPopupMenu;

import javax.swing.SwingConstants;

import javax.swing.text.StyledEditorKit.FontSizeAction;



import prefuse.Constants;

import prefuse.Display;

import prefuse.Visualization;

import prefuse.action.ActionList;

import prefuse.action.RepaintAction;

import prefuse.action.assignment.ColorAction;

import prefuse.action.assignment.DataColorAction;

import prefuse.action.assignment.DataShapeAction;

import prefuse.action.assignment.SizeAction;

import prefuse.action.layout.AxisLabelLayout;

import prefuse.action.layout.AxisLayout;

import prefuse.controls.Control;

import prefuse.controls.ControlAdapter;

import prefuse.controls.DragControl;

import prefuse.controls.PanControl;

import prefuse.controls.ToolTipControl;

import prefuse.controls.WheelZoomControl;

import prefuse.controls.ZoomControl;

import prefuse.controls.ZoomToFitControl;

import prefuse.data.Table;

import prefuse.data.io.DelimitedTextTableReader;

import prefuse.render.AbstractShapeRenderer;

import prefuse.render.AxisRenderer;

import prefuse.render.DefaultRendererFactory;

import prefuse.render.Renderer;

import prefuse.render.RendererFactory;

import prefuse.render.ShapeRenderer;

import prefuse.util.ColorLib;

import prefuse.util.ui.UILib;

import prefuse.visual.VisualItem;

import prefuse.visual.VisualTable;

import prefuse.visual.expression.VisiblePredicate;



public class Poltu extends Display {



    private static final String group = "data";

    

    private ShapeRenderer m_shapeR = new ShapeRenderer(2);

    

    public Poltu(Table t, String xfield, String yfield) {

        this(t, xfield, yfield, null);

    }

    

    public Poltu(Table t, String xfield, String yfield, String sfield) {

        super(new Visualization());

        

        

        final VisualTable vt = m_vis.addTable(group, t);

        

        //using axis layout to plot the visual items on the basis of the selected fields

        AxisLayout x_axis = new AxisLayout(group, xfield, 

                Constants.X_AXIS, VisiblePredicate.TRUE);

        m_vis.putAction("x", x_axis);

        

        AxisLayout y_axis = new AxisLayout(group, yfield, 

                Constants.Y_AXIS, VisiblePredicate.TRUE);

        m_vis.putAction("y", y_axis);



        //making a color palette to color the items on the basis of attendance

        int[] palette = new int[101];

        

        for (int i =0;i<100; i++){

		palette[i] = ColorLib.rgb(0,2*i,0); 

        }

        

        DataColorAction color = new DataColorAction(group, "Attendance", Constants.NOMINAL, 

                VisualItem.STROKECOLOR, palette);

        

        m_vis.putAction("color", color);

        

        //coloring the lines of the x-axis

        ColorAction color2 = new ColorAction("xlab", VisualItem.STROKECOLOR, ColorLib.rgb(200,0,0));

        m_vis.putAction("color2", color2);

        

        //coloring the lines of the y-axis

        ColorAction color3 = new ColorAction("ylab", VisualItem.STROKECOLOR, ColorLib.rgb(0,0,200));

        m_vis.putAction("color3", color3);

        

        //coloring the labels of the x-axis

        ColorAction color4 = new ColorAction("xlab", VisualItem.TEXTCOLOR, ColorLib.rgb(200,0,0));

        m_vis.putAction("color4", color4);

        

        //coloring the labels of the y-axis

        ColorAction color5 = new ColorAction("ylab", VisualItem.TEXTCOLOR, ColorLib.rgb(0, 0, 200));

        m_vis.putAction("color5", color5);

        

        //giving shapes to the visual items on the basis of the specified field

        DataShapeAction shape = new DataShapeAction(group, sfield);

        m_vis.putAction("shape", shape);

        

        //specifying the layout of the x-axis

        AxisLabelLayout xlabels = new AxisLabelLayout("xlab", x_axis);

        xlabels.setSpacing(70);

        m_vis.putAction("xlabels", xlabels);

        

        //specifying the layout of the y-axis

        AxisLabelLayout ylabels = new AxisLabelLayout("ylab", y_axis);

        ylabels.setSpacing(20);

        m_vis.putAction("ylabels", ylabels);

        

        //setting up the rendering of the items

        m_vis.setRendererFactory(new RendererFactory() {

            AbstractShapeRenderer sr = m_shapeR;

            Renderer arY = new AxisRenderer(Constants.FAR_LEFT, Constants.TOP);

            Renderer arX = new AxisRenderer(Constants.CENTER, Constants.FAR_BOTTOM);

            

            public Renderer getRenderer(VisualItem item) {

                return item.isInGroup("ylab") ? arY :

                       item.isInGroup("xlab") ? arX : sr;

            }

        });

        

        ActionList draw = new ActionList();

        draw.add(x_axis);

        draw.add(y_axis);

        draw.add(xlabels);

        draw.add(ylabels);

        draw.add(color);

        draw.add(color2);

        draw.add(color3);

      draw.add(color4);

        draw.add(color5);

        if ( sfield != null )

        	{draw.add(shape);

            }

              



        draw.add(new RepaintAction());

        m_vis.putAction("draw", draw);

        



        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        setSize(700,450);

        setHighQuality(true);

        

        addControlListener(new DragControl());

		addControlListener(new PanControl());

		addControlListener(new ZoomControl());

        addControlListener(new WheelZoomControl());

        addControlListener(new ZoomToFitControl());

    	setBackground(Color.BLACK);        

        

        

        //when the mouse pointer hovers over a visual item, it's X and Y co-ordinates are displayed    	

        ToolTipControl ttc = new ToolTipControl(new String[] {xfield,yfield});

        addControlListener(ttc);

        

        

        //when a visual item is clicked

        Control hoverc = new ControlAdapter() {

            public void itemClicked(VisualItem item, MouseEvent e) {

                if ( item.isInGroup(group) ) {

                	int i = item.getRow();

                  String Name = (String) vt.getString(i, "MP name");

                  String party = (String) vt.getString(i, "Political party");

                  String state = (String) vt.getString(i, "State");

                  String age = (String) vt.getString(i, "Age");

                  String attend = (String) vt.getString(i, "Attendance");

                  JPopupMenu jpub = new JPopupMenu();

              	jpub.add("Name: " + Name);

              	jpub.add("Political Party: " + party);

              	jpub.add("State: " + state);

              	jpub.add("Age: " + age);

              	jpub.add("Attendance: " + attend);

              	jpub.show(e.getComponent(),(int) item.getX(),

              			(int) item.getY()); 

              		}

                }

        };        

        addControlListener(hoverc);

        

        

             

        m_vis.run("draw");

    }

    

    public int getPointSize() {

        return m_shapeR.getBaseSize();

    }

    

    public void setPointSize(int size) {

        m_shapeR.setBaseSize(size);

        repaint();

    }

    

    //for setting up the toolbar to ask the viewer to put the values for the x-axis, y-axis and the shape

    public static JToolBar getEncodingToolbar(final Poltu sp,

            final String xfield, final String yfield, final String sfield)

    {

        int spacing = 15;

        

        Table t = (Table)sp.getVisualization().getSourceData(group);

        String[] colnames = new String[t.getColumnCount()];

        for ( int i=0; i<colnames.length; ++i )

            colnames[i] = t.getColumnName(i);

        

        JToolBar toolbar = new JToolBar();

        toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));

        toolbar.add(Box.createHorizontalStrut(spacing));

        

        //box for taking the value of what appears on the x-axis and then redrawing the display

        final JComboBox xcb = new JComboBox(colnames);

        xcb.setSelectedItem(xfield);

        xcb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Visualization vis = sp.getVisualization();

                AxisLayout xaxis = (AxisLayout)vis.getAction("x");

                xaxis.setDataField((String)xcb.getSelectedItem());

                ActionList draw = (ActionList)vis.getAction("draw");

                AxisLabelLayout xlabels = (AxisLabelLayout)vis.getAction("xlabels");

                xlabels = new AxisLabelLayout("xlab", xaxis);

                xlabels.setSpacing(70);

                vis.putAction("xlabels", xlabels);

                ColorAction colorax = (ColorAction)vis.getAction("color2");

                colorax = new ColorAction("xlab", VisualItem.STROKECOLOR, ColorLib.rgb(200,0,0));

                vis.putAction("colorax", colorax);

                ColorAction colorlb = (ColorAction)vis.getAction("color4");

                colorlb = new ColorAction("xlab", VisualItem.TEXTCOLOR, ColorLib.rgb(200,0,0));

                vis.putAction("colorlb", colorlb);

                draw.add(xlabels);

                draw.add(colorax);

                draw.add(colorlb);

                

                vis.run("draw");



            }

        });

        toolbar.add(new JLabel("X: "));

        toolbar.add(xcb);

        toolbar.add(Box.createHorizontalStrut(2*spacing));

        

        //box for taking the value of what appears on the y-axis and then redrawing the display



        final JComboBox ycb = new JComboBox(colnames);

        ycb.setSelectedItem(yfield);

        ycb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Visualization vis = sp.getVisualization();

                AxisLayout yaxis = (AxisLayout)vis.getAction("y");

                yaxis.setDataField((String)ycb.getSelectedItem());

                ActionList draw = (ActionList)vis.getAction("draw");

                AxisLabelLayout ylabels = (AxisLabelLayout)vis.getAction("ylabels");

                ylabels = new AxisLabelLayout("ylab", yaxis);

                ylabels.setSpacing(15);

                vis.putAction("ylabels", ylabels);

                ColorAction colorax = (ColorAction)vis.getAction("color3");

                colorax = new ColorAction("ylab", VisualItem.STROKECOLOR, ColorLib.rgb(0,0,200));

                vis.putAction("colorax", colorax);

                ColorAction colorlb = (ColorAction)vis.getAction("color5");

                colorlb = new ColorAction("ylab", VisualItem.TEXTCOLOR, ColorLib.rgb(0,0,200));

                vis.putAction("colorlb", colorlb);

                draw.add(ylabels);

                draw.add(colorax);

                draw.add(colorlb);

                vis.run("draw");

            }

        });

        toolbar.add(new JLabel("Y: "));

        toolbar.add(ycb);

        toolbar.add(Box.createHorizontalStrut(2*spacing));

        

        //box for taking the value on which the shapes are based and then redrawing the display

        final JComboBox scb = new JComboBox(colnames);

        scb.setSelectedItem(sfield);

        scb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Visualization vis = sp.getVisualization();

                DataShapeAction s = (DataShapeAction)vis.getAction("shape");

                s.setDataField((String)scb.getSelectedItem());

                vis.run("draw");

            }

        });

        toolbar.add(new JLabel("Shape: "));

        toolbar.add(scb);

        toolbar.add(Box.createHorizontalStrut(spacing));

        toolbar.add(Box.createHorizontalGlue());

        

        return toolbar;

    }

    

    public static void main(String[] argv) {

        UILib.setPlatformLookAndFeel();



        String data = "/Book1.txt";

        String xfield = "State";

        String yfield = "Political party";

        String sfield = "Educational qualifications";

        

        

        final Poltu sp = demo(data, xfield, yfield, sfield);

        JToolBar toolbar = getEncodingToolbar(sp, xfield, yfield, sfield);

        

        

        

        JFrame frame = new JFrame("15th Lok Sabha");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(toolbar, BorderLayout.SOUTH);

        frame.getContentPane().add(sp, BorderLayout.CENTER);

        frame.pack();

        frame.setVisible(true);

    }

    

    public static Poltu demo(String data, String xfield, String yfield) {

        return demo(data, xfield, yfield, null);

    }

    

    public static Poltu demo(String data, String xfield,

                                   String yfield, String sfield)

    //input reader from the tab separated variables file "Book1.txt"

    {

        Table table = null;

        try {

            table = new DelimitedTextTableReader().readTable(data);

        } catch ( Exception e ) {

            e.printStackTrace();

            return null;

        }

        Poltu scatter = new Poltu(table, xfield, yfield, sfield);

        scatter.setPointSize(20);

        return scatter;

    }

    

   

    

    

}