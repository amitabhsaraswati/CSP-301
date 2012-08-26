package bookviz;

import java.util.Iterator;

import prefuse.controls.ControlAdapter;
import prefuse.util.ColorLib;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
//when the mouse goes over a NodeItem then its text gets highlighted purple and its neighbors' texts get highlighted white
public class Highlight extends ControlAdapter {
	public int temp;
	public int[] temp2 = new int[1000];
public void itemEntered(VisualItem item, java.awt.event.MouseEvent e){
	if (item instanceof NodeItem){/*when the item entered is a NodeItem then its text colour is stored in the temporary variable
	temp and the text colours of its neighbors are stored in a integer array temp2 */
NodeItem n = (NodeItem) item;
temp = n.getTextColor();
n.setTextColor(ColorLib.rgb(200, 0, 200));
Iterator a = n.neighbors();
int i=0;
while (a.hasNext()){
	NodeItem b= (NodeItem) a.next();
	temp2[i] = b.getTextColor();
	b.setTextColor(ColorLib.rgb(200, 200, 200));
	i++;
}}
}
public void itemExited(VisualItem item, java.awt.event.MouseEvent e){
	if (item instanceof NodeItem){
		/*when the exited item is a NodeItem then its original text colour is restored from temp and the 
		 * text colours of its neighbors are restored using data from int array temp2 */
NodeItem n = (NodeItem) item;
n.setTextColor(temp);
Iterator a = n.neighbors();
int i=0;
while (a.hasNext()){
	NodeItem b= (NodeItem) a.next();
	int c= temp2[i];
	b.setTextColor(c);

	i++;
}}

}}
