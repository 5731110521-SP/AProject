package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import entity.Player;
import input.InputUtility;

public class Choose extends JPanel {
	private int level;
	private Player player;
	private int[] x;
	private int choose,choosed;

	public Choose(int level,int choosed,int player) {
		super();
		this.setPreferredSize(new Dimension(640, 480));
		this.setBackground(Color.GRAY);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		this.level = level;
		if(player==1)this.player=Login.player1;
		else this.player=Login.player2;
		x = new int[6];
		for (int i : x) {
			i = 0;
		}
		choose = -1;
		this.choosed=choosed;

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				InputUtility.mouseLeftRelease();
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getX() >= 483 && arg0.getX() <= 483 + Resource.choose[0].getWidth() && arg0.getY() >= 303
						&& arg0.getY() <= 303 + Resource.choose[0].getHeight()) {
					choose = 0;
					if(level>=4 && choosed!=0)InputUtility.mouseLeftDown();
				} else if (arg0.getX() >= 430 && arg0.getX() <= 430 + Resource.choose[1].getWidth() && arg0.getY() >= 95
						&& arg0.getY() <= 95 + Resource.choose[1].getHeight()) {
					choose = 1;
					if(choosed!=1)InputUtility.mouseLeftDown();
				} else if (arg0.getX() >= 186 && arg0.getX() <= 186 + Resource.choose[2].getWidth()
						&& arg0.getY() >= 283 && arg0.getY() <= 283 + Resource.choose[2].getHeight()) {
					choose = 2;
					if(level>=2 && choosed!=2)InputUtility.mouseLeftDown();
				} else if (arg0.getX() >= 11 && arg0.getX() <= 11 + Resource.choose[3].getWidth() && arg0.getY() >= 355
						&& arg0.getY() <= 355 + Resource.choose[3].getHeight()) {
					choose = 3;
					if(level>=3 && choosed!=3)InputUtility.mouseLeftDown();
				} else if (arg0.getX() >= 190 && arg0.getX() <= 190 + Resource.choose[4].getWidth()
						&& arg0.getY() >= 103 && arg0.getY() <= 103 + Resource.choose[4].getHeight()) {
					choose = 4;
					if(choosed!=4) InputUtility.mouseLeftDown();
				} else if (arg0.getX() >= 7 && arg0.getX() <= 7 + Resource.choose[5].getWidth() && arg0.getY() >= 83
						&& arg0.getY() <= 83 + Resource.choose[5].getHeight()) {
					choose = 5;
					if(level>=5 && choosed!=5)InputUtility.mouseLeftDown();
				}
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (arg0.getX() >= 483 && arg0.getX() <= 483 + Resource.choose[0].getWidth() && arg0.getY() >= 303
						&& arg0.getY() <= 303 + Resource.choose[0].getHeight()) {
					x[0] = 3;
				} else {
					x[0] = 0;
				}
				if (arg0.getX() >= 430 && arg0.getX() <= 430 + Resource.choose[1].getWidth() && arg0.getY() >= 95
						&& arg0.getY() <= 95 + Resource.choose[1].getHeight()) {
					x[1] = 3;
				} else {
					x[1] = 0;
				}
				if (arg0.getX() >= 186 && arg0.getX() <= 186 + Resource.choose[2].getWidth() && arg0.getY() >= 283
						&& arg0.getY() <= 283 + Resource.choose[2].getHeight()) {
					x[2] = 3;
				} else {
					x[2] = 0;
				}
				if (arg0.getX() >= 11 && arg0.getX() <= 11 + Resource.choose[3].getWidth() && arg0.getY() >= 355
						&& arg0.getY() <= 355 + Resource.choose[3].getHeight()) {
					x[3] = 3;
				} else {
					x[3] = 0;
				}
				if (arg0.getX() >= 190 && arg0.getX() <= 190 + Resource.choose[4].getWidth() && arg0.getY() >= 103
						&& arg0.getY() <= 103 + Resource.choose[4].getHeight()) {
					x[4] = 3;
				} else {
					x[4] = 0;
				}
				if (arg0.getX() >= 7 && arg0.getX() <= 7 + Resource.choose[5].getWidth() && arg0.getY() >= 83
						&& arg0.getY() <= 83 + Resource.choose[5].getHeight()) {
					x[5] = 3;
				} else {
					x[5] = 0;
				}
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(Resource.choose[6], 0, 0, null);
		if(choosed!=1) g2.drawImage(Resource.choose[1], 430 + x[1], 95 + x[1], null);
		if(choosed!=4) g2.drawImage(Resource.choose[4], 190 + x[4], 103 + x[4], null);

		if (level >= 2 && choosed!=2)
			g2.drawImage(Resource.choose[2], 186 + x[2], 283 + x[2], null);
		if (level >= 3 && choosed!=3)
			g2.drawImage(Resource.choose[3], 11 + x[3], 355 + x[3], null);
		if (level >= 4 && choosed!=0)
			g2.drawImage(Resource.choose[0], 483 + x[0], 303 + x[0], null);
		if (level >= 5 && choosed!=5)
			g2.drawImage(Resource.choose[5], 7 + x[5], 83 + x[5], null);
		// g2.drawImage(Resource.choose[6], 0, 0, null);
		// g2.drawImage(Resource.choose[1], 430, 95, null);
		// g2.drawImage(Resource.choose[4], 190, 103, null);
		//
		// if(level>=2)g2.drawImage(Resource.choose[2], 186, 283, null);
		// if(level>=3)g2.drawImage(Resource.choose[3], 11, 355, null);
		// if(level>=4)g2.drawImage(Resource.choose[0], 483, 303, null);
		// if(level>=5)g2.drawImage(Resource.choose[5], 7, 83, null);
		
		g2.setFont(new Font("Tahoma", Font.BOLD, 30));
		FontMetrics fm = g2.getFontMetrics();
		Rectangle2D r2 = fm.getStringBounds(player.getName()+" choose your Character.", g2);
		g2.setColor(Color.WHITE);
		g2.drawString(player.getName()+" choose your Character.", GameScreen.width/2-(int)r2.getWidth()/2, 40);
	}

	public int update() {
		if (InputUtility.isLeftClickTriggered()) {
			InputUtility.updateInputState();
			setVisible(false);
			return choose;
		}
		return -1;
	}
}
