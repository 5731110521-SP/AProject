package render;

import java.awt.Graphics2D;

public interface IRenderable {
	public void draw(Graphics2D g);
	public void update();
	public boolean getFlashing();
	public boolean isVisible();
	public int getZ();
}
