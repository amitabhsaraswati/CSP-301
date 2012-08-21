package bookviz;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import prefuse.render.AbstractShapeRenderer;
import prefuse.visual.VisualItem;

public class FinalRenderer extends AbstractShapeRenderer{
	protected Ellipse2D m_box = new Ellipse2D.Double();
	@Override
	protected Shape getRawShape(VisualItem item){
		m_box.setFrame(item.getX(), item.getY(),
				(Integer) item.get("age")/3,
				(Integer) item.get("age")/3);
				return m_box;
				}
		}
