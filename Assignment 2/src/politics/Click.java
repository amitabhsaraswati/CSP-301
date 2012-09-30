package politics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPopupMenu;
import prefuse.controls.ControlAdapter;
import prefuse.controls.Control;
import prefuse.visual.NodeItem;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;
import prefuse.visual.tuple.TableNodeItem;

@SuppressWarnings("unused")
// This class decides what is displayed when any item in the visualization is clicked
public class Click extends ControlAdapter implements Control{
	@SuppressWarnings("deprecation")
	public void itemClicked(VisualItem item, MouseEvent e){
	if(item instanceof TableNodeItem){/*If the item is a NodeItem then its "Label", "ID" and Orientation are displayed */
	String Name = ((String) item.get(1));
	String State = (String) item.get(2);
	String PP = (String) item.get(3);
	String Ed = (String) item.get(4);
	String Age = (String) item.get(5);
	String Att = (String) item.get(6);
	JPopupMenu jpub = new JPopupMenu();
	jpub.add("Name: " + Name);
	jpub.add("State: " + State);
	jpub.add("Political Party: " + PP);
	jpub.add("Educational Qualifications: " + Ed);
	jpub.add("Age: " + Age);
	jpub.add("Attendance: " + Att);
	jpub.show(//e.getComponent(),(int) item.getX(),
	//(int) item.getY()
	);
		}
	
	}
}
