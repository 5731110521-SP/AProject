import javax.swing.JComponent;
import javax.swing.JFrame;
import character.Character;
import render.Choose;
import render.GameScreen;
import render.Home;
import render.Login;
import render.Setting;
import render.Winner;
import entity.GameLogic;
import entity.Player;
import entity.Time;
import logic.Name;
import logic.NameBlankException;

public class Main {
	private static GameLogic logic;
	private static JFrame frame;
	private static Home home;
	private static Setting setting;
	private static Login login;
	private static Choose choose;
	private static GameScreen screen;
	private static Winner winner;

	public static void main(String[] args) {
		frame = new JFrame("Get em' all");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		home = new Home();
		setting = new Setting();
		JComponent currentPanel = null;
		while (true) {
			int next = home(currentPanel);
			currentPanel = home;
			if (next == 0) {
				Name.readFile();
				if (Login.player[0] == null) {
					login(currentPanel, 1);
					currentPanel = login;
					login(currentPanel, 2);
					currentPanel = login;
				}else{
					Login.player[0]=new Player(1,Login.player[0].getName());
					Login.player[1]=new Player(2,Login.player[1].getName());
				}

				
				int Player1Choose,Player2Choose;
				if (Login.player[0].getLevel() <= Login.player[1].getLevel()) {
					Player1Choose = choose(currentPanel, 1, -1);
					currentPanel = choose;
					Player2Choose = choose(currentPanel, 2,  Player1Choose);
					currentPanel = choose;
				} else {
					Player2Choose = choose(currentPanel, 2,  -1);
					currentPanel = choose;
					Player1Choose = choose(currentPanel, 1, Player2Choose);
					currentPanel = choose;
				}

				game(currentPanel, Player1Choose, Player2Choose);
				currentPanel = screen;
				winner(currentPanel);
				currentPanel = winner;
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
			} catch (NameBlankException e) {
			}
		}
	}

	private static int choose(JComponent currentPanel,int i, int choosed) {
		choose = new Choose(choosed, i);
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
			if(((Character)GameLogic.character[0]).isJump()){
				synchronized (GameLogic.character[0]) {
					GameLogic.character[0].notifyAll();
				}
			}
			if(((Character)GameLogic.character[1]).isJump()){
				synchronized (GameLogic.character[1]) {
					GameLogic.character[1].notifyAll();
				}
			}
			if (Time.isEnd)
				return;
		}	
	}
	
	public static void winner(JComponent currentPanel) {
		winner = new Winner();
		winner.setVisible(true);
		frame.getContentPane().add(winner);
		frame.remove(currentPanel);
		frame.pack();
		winner.requestFocus();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			winner.repaint();
			if (winner.update()) {
				return;
			}
		}
	}
}
