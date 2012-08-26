package bookviz;

import java.util.Iterator;

import prefuse.controls.ControlAdapter;
import prefuse.util.ColorLib;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
//when the mouse goes over a NodeItem then it gets highlighted purple and its neighbors get highlighted white
public class Highlight extends ControlAdapter {
	public int temp;
	public int[] temp2 = new int[1000];
public void itemEntered(VisualItem item, java.awt.event.MouseEvent e){
	if (item instanceof NodeItem){/* */
NodeItem n = (NodeItem) item;
temp = n.getFillColor();
n.setFillColor(ColorLib.rgb(200, 0, 200));
Iterator a = n.neighbors();
int i=0;
while (a.hasNext()){
	NodeItem b= (NodeItem) a.next();
	temp2[i] = b.getFillColor();
	b.setFillColor(ColorLib.rgb(200, 200, 200));
	i++;
}}
}
public void itemExited(VisualItem item, java.awt.event.MouseEvent e){
	if (item instanceof NodeItem){
NodeItem n = (NodeItem) item;
n.setFillColor(temp);
Iterator a = n.neighbors();
int i=0;
while (a.hasNext()){
	NodeItem b= (NodeItem) a.next();
	int c= temp2[i];
	b.setFillColor(c);

	i++;
}}

}}
