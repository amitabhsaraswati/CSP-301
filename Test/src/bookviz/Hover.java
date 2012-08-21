package bookviz;


import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPopupMenu;
import prefuse.controls.ControlAdapter;
import prefuse.controls.Control;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;


public class Hover extends ControlAdapter implements Control{
	public void itemClicked(VisualItem item, MouseEvent e){
	if(item instanceof NodeItem){
	String Label = ((String) item.get("label"));
	int Id = (Integer) item.get("id");
	JPopupMenu jpub = new JPopupMenu();
	jpub.add("Label: " + Label);
	jpub.add("ID: " + Id);
	jpub.show(e.getComponent(),(int) item.getX(),
	(int) item.getY());
		}
	}
}
