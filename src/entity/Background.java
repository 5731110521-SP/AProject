package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import render.IRenderable;
import render.Resource;

public class Background implements IRenderable{

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		BufferedImage bg = Resource.bg[0];
		g.drawImage(bg,0,0,bg.getWidth(),bg.getHeight(),null);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -999;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getFlashing() {
		// TODO Auto-generated method stub
		return false;
	}

}
