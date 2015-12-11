import java.awt.Frame;

import javax.swing.JFrame;

import render.GameScreen;
import render.Home;
import render.How;
import render.Setting;
import entity.GameLogic;

public class Main {
	private static GameLogic logic;
	private static JFrame frame;
	private static Home home;
	private static How how;
	private static Setting setting;

	public static void main(String[] args) {
		frame = new JFrame("Get em' all");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		home = new Home();
		how = new How();
		setting=new Setting();
		
		while (true) {
			int next = home();
			if (next == 0) {
				game();
			}else if(next==1){
				how();
			}else if(next==2){
				setting();
			}
		}

	}

	public static int home() {
		home.setVisible(true);
		frame.getContentPane().add(home);
		frame.pack();
		home.requestFocus();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			home.repaint();
			int next = home.update();
			if (next != -1) {
				return next;
			}
		}

	}
	
	public static void how(){
		how.setVisible(true);
		frame.getContentPane().add(how);
		frame.pack();
		how.requestFocus();
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			how.repaint();
			if (how.update()) {
				return;
			}
		}
	}
	
	public static void setting(){
		setting.setVisible(true);
		frame.getContentPane().add(setting);
		frame.pack();
		setting.requestFocus();
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setting.repaint();
			if (setting.update()) {
				return;
			}
		}
	}

	public static void game() {
		GameScreen screen = new GameScreen();
		logic = new GameLogic();
		
		frame.getContentPane().add(screen);
		frame.pack();
		screen.requestFocus();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
			screen.repaint();
			logic.logicUpdate();
		}
	}
}
