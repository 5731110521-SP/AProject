import java.awt.Frame;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import render.Choose;
import render.GameScreen;
import render.Home;
import render.How;
import render.Login;
import render.RenderableHolder;
import render.Resource;
import render.Setting;
import entity.GameLogic;
import logic.MyException;
import logic.Name;

public class Main {
	private static GameLogic logic;
	private static JFrame frame;
	private static Home home;
	private static How how;
	private static Setting setting;
	private static Login login;
	private static Choose choose;
	private static GameScreen screen;

	public static void main(String[] args) {
		frame = new JFrame("Get em' all");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		home = new Home();
		how = new How();
		setting = new Setting();
		JComponent currentPanel = null;
		while (true) {
			int next = home(currentPanel);
			currentPanel = home;
			if (next == 0) {
				int Player1Level = login(currentPanel, 1);
				currentPanel = login;
				int Player2Level = login(currentPanel, 2);
				currentPanel = login;
				
				int Player1Choose,Player2Choose;
				if(Player1Level<=Player2Level){
					Player1Choose = choose(currentPanel,1,Player1Level,-1);
					currentPanel = choose;
					Player2Choose = choose(currentPanel,2,Player2Level,Player1Choose);
					currentPanel = choose;
				}else{
					Player2Choose = choose(currentPanel,2,Player2Level,-1);
					currentPanel = choose;
					Player1Choose = choose(currentPanel,1,Player1Level,Player2Choose);
					currentPanel = choose;
				}
				
				game(currentPanel,Player1Choose,Player2Choose);
				currentPanel = screen;
				Name.createFile();
			} else if (next == 1) {
				how(currentPanel);
				currentPanel = how;
			} else if (next == 2) {
				setting(currentPanel);
				currentPanel = setting;
			}
		}

	}

	public static int home(JComponent currentPanel) {
		home.setVisible(true);
		frame.getContentPane().add(home);
		if (currentPanel != null)
			frame.remove(currentPanel);
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

	public static void how(JComponent currentPanel) {
		how.setVisible(true);
		frame.getContentPane().add(how);
		frame.remove(currentPanel);
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

	public static void setting(JComponent currentPanel) {
		setting.setVisible(true);
		frame.getContentPane().add(setting);
		frame.remove(currentPanel);
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

	public static int login(JComponent currentPanel, int i) {
		login = new Login(i);
		login.setVisible(true);
		frame.remove(currentPanel);
		frame.getContentPane().add(login);
		frame.pack();
		login.requestFocus();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			login.repaint();
			try {
				if (login.update()) {
					return Name.findName(login.getName());
				}
			} catch (MyException e) {
			}
		}
	}

	private static int choose(JComponent currentPanel,int i, int level,int choosed) {
		choose = new Choose(level,choosed);
		choose.setVisible(true);
		frame.getContentPane().add(choose);
		frame.remove(currentPanel);
		frame.pack();
		choose.requestFocus();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			choose.repaint();
			int c = choose.update();
			if (c!=-1) {
				return c;
			}
		}

	}

	public static void game(JComponent currentPanel,int player1Choose,int player2Choose) {
		screen = new GameScreen();
		logic = new GameLogic(player1Choose,player2Choose);

		frame.getContentPane().add(screen);
		frame.remove(currentPanel);
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
