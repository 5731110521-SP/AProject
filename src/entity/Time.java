package entity;

import java.awt.Graphics2D;

import org.w3c.dom.NamedNodeMap;

import render.IRenderable;

public class Time implements IRenderable {
	public static boolean isPlay;
	private double startTime;
	static{
		isPlay=true;
	}
	
	private int state;
	
	public Time() {
		state=0;
		startTime=System.nanoTime()*1000000000;
	}

	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2 = (Graphics2D) g;
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void update() {
		double lastTime = System.nanoTime()*1000000000;
		if(lastTime-startTime>=1){
			state++;
			startTime=lastTime;
		}
		if(state>=4){
			isPlay=true;
		}
		if(state>=124){
			isPlay=false;
		}
	}

	@Override
	public boolean getFlashing() {
		// TODO Auto-generated method stub
		return false;
	}

}
