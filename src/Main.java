import java.awt.Frame;

import javax.swing.JFrame;

import render.GameScreen;
import render.Home;
import entity.GameLogic;

public class Main {
	private static GameLogic logic;
	private static JFrame frame;
	public static void main(String[] args){
		frame = new JFrame("Get em' all");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int next = home();
		if(next==0) {
			game();
		}
		
	}
	
	public static int home(){
		Home home = new Home();
		frame.getContentPane().add(home);
		frame.setVisible(true);
		frame.pack();
		home.requestFocus();
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			home.repaint();
			if(home.update()) {
				return 0;
			}
		}
		
	}
	public static void game(){
		GameScreen screen = new GameScreen();
		logic = new GameLogic();
		frame.getContentPane().add(screen);
		frame.setVisible(true);
		frame.pack();
		screen.requestFocus();
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			screen.repaint();
			logic.logicUpdate();
		}
	}
}
