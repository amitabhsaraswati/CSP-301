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

@SuppressWarnings("unused")
// This class decides what is displayed when any item in the visualization is clicked
public class Hover extends ControlAdapter implements Control{
	public void itemClicked(VisualItem item, MouseEvent e){
	if(item instanceof NodeItem){/*If the item is a NodeItem then its "Label", "ID" and Orientation are displayed */
	String Label = ((String) item.get("label"));
	int Id = (Integer) item.get("id");
	String Value = (String) item.get("value");
	JPopupMenu jpub = new JPopupMenu();
	jpub.add("Label: " + Label);
	jpub.add("ID: " + Id);
	jpub.add("Orientation: " + Value);
	jpub.show(e.getComponent(),(int) item.getX(),
	(int) item.getY());
		}
	else if(item instanceof EdgeItem){
		/*If the item is a EdgeItem then the labels, IDs and orientations of its source and target nodes are displayed */
		String label1 = (String) ((EdgeItem) item).getSourceItem().get("label");
		String label2 = (String) ((EdgeItem) item).getTargetItem().get("label");
		
		int Id1 = (Integer) ((EdgeItem) item).getSourceItem().get("id");
		int Id2 = (Integer) ((EdgeItem) item).getTargetItem().get("id");
		
		String Value1 = (String) ((EdgeItem)item).getSourceItem().get("value");
		String Value2 = (String) ((EdgeItem)item).getTargetItem().get("value");
		
		JPopupMenu jpub = new JPopupMenu();
		jpub.add("Labels: " + label1 + " to " + label2);
		jpub.add("IDs: " + Id1 + " to " + Id2);
		jpub.add("Orientations: " + Value1 + " to " + Value2);
		jpub.show(e.getComponent(),(int) item.getX(),
		(int) item.getY());
	}
	}
}
