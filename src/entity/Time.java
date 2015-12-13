package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.NamedNodeMap;

import render.GameScreen;
import render.IRenderable;
import render.Login;

public class Time implements IRenderable {
	public static boolean isPlay,isend,ishasWinner;
	private double startTime;
	private int timeEnd,time,state;
	private String winner;
	static{
		isPlay=false;
		isend=false;
	}
	
	public Time() {
		state=0;
		startTime=System.nanoTime()/1000000000;
		timeEnd=120;
		winner="";
		time=timeEnd;
		isPlay=false;
		isend=false;
		ishasWinner = false;
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D r2 = fm.getStringBounds("3", g);
		if(state==1)g.drawString("3", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2);
		else if(state==2)g.drawString("2", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2);
		else if(state==3)g.drawString("1", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2);
		else if(state==4){
			r2 = fm.getStringBounds("Start", g);
			g.drawString("Start", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2);
		}
		else if(state==timeEnd+5){
			r2 = fm.getStringBounds("Time out", g);
			g.drawString("Time out", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2);
		}else if((state>=timeEnd+6 && state<=timeEnd+8) || (state>=timeEnd+12 && state<=timeEnd+14)){
			r2 = fm.getStringBounds(winner, g);
			g.drawString(winner, GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2);
		}else if(state==timeEnd+11){
			r2 = fm.getStringBounds("KO", g);
			g.drawString("KO", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2);
		}
		r2 = fm.getStringBounds(Integer.toString(time), g);
		g.drawString(Integer.toString(time), GameScreen.width/2-(int)r2.getWidth()/2, 40);
		
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
		double lastTime = System.nanoTime()/1000000000;
		if(lastTime-startTime>=1 &&!isend){
			state++;
			startTime=lastTime;
			if(state<=timeEnd+4 &&state>=5 &&!ishasWinner)time--;
		}
		if(ishasWinner){
			isPlay=false;
			if(state<timeEnd+4)state=timeEnd+10;
			else if(state==timeEnd+11)getWinnerAn();
			else if(state==timeEnd+15)isend=true;
		}else if(state<4){
			isPlay=false;
		}else if(state<timeEnd+4){
			isPlay=true;
		}else if(state==timeEnd+4){
			isPlay=false;
			getWinnerAn();
		}else if(state==timeEnd+9){
			isend=true;
		}
		
	}

	@Override
	public boolean getFlashing() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void getWinnerAn(){
		int i =GameLogic.getWinner();
		if(i==1){
			winner="The winner is "+Login.player1.getName();
		}else if(i==2){
			winner="The winner is "+Login.player2.getName();
		}else{
			winner="Draw";
		}
	}

}
