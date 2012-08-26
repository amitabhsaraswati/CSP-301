package bookviz;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import prefuse.render.LabelRenderer;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

public class RenderersCustom extends LabelRenderer {
//displays the id of each node on the node when it is created
	protected String getText(VisualItem item) 
	{	
		if(item instanceof NodeItem)
		{return (Integer.toString((Integer) (item.get("id"))));
			}		
	
		else {
			return "edge";
		}
	}
}

