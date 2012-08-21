package bookviz;


import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPopupMenu;
import prefuse.controls.ControlAdapter;
import prefuse.controls.Control;
import prefuse.visual.NodeItem;
import prefuse.visual.EdgeItem;
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
	else if(item instanceof EdgeItem){
		String label1 = (String) ((EdgeItem) item).getSourceItem().get("label");
		String label2 = (String) ((EdgeItem) item).getTargetItem().get("label");
		
		int Id1 = (Integer) ((EdgeItem) item).getSourceItem().get("id");
		int Id2 = (Integer) ((EdgeItem) item).getTargetItem().get("id");
		
		JPopupMenu jpub = new JPopupMenu();
		jpub.add("Labels: " + label1 + " to " + label2);
		jpub.add("IDs: " + Id1 + " to " + Id2);
		jpub.show(e.getComponent(),(int) item.getX(),
		(int) item.getY());
	}
	}
}
