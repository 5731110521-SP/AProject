package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import entity.GameLogic;
import input.InputUtility;
import logic.Name;

public class Winner extends JPanel{
	private int winner;
	public Winner() {
		super();
		this.setPreferredSize(new Dimension(640,480));
		this.setBackground(Color.GRAY);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		this.winner = GameLogic.getWinner();
		if(winner==1){
			Name.levelUp(Login.player[0].getName());
		}else if(winner==2){
			Name.levelUp(Login.player[1].getName());
		}
		Name.createFile();
		
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
				InputUtility.mouseLeftDown();
			}
		});
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2= (Graphics2D) g;
		g2.setFont(new Font("Tahoma", Font.BOLD, 30));
		g2.setColor(Color.WHITE);
		if(winner==1 || winner==2){
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D r2 = fm.getStringBounds(Login.player[winner-1].getName()+" is th winner.", g);
			g2.drawString(Login.player[winner-1].getName()+" is th winner.", GameScreen.width / 2 - (int) r2.getWidth() / 2, 270);			
			g2.setFont(new Font("Tahoma", 0, 20));
			fm = g.getFontMetrics();
			r2 = fm.getStringBounds("Level UP! "+Login.player[winner-1].getLevel()+" > "+(Login.player[winner-1].getLevel()+1), g);
			g2.drawString("Level UP! "+Login.player[winner-1].getLevel()+" > "+(Login.player[winner-1].getLevel()+1), GameScreen.width / 2 - (int) r2.getWidth() / 2, 300);
		}else{
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D r2 = fm.getStringBounds("Draw", g);
			g2.drawString("Draw", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.width / 2-15);
		}
	}

	public boolean update() {
		if(InputUtility.isLeftClickTriggered()){
			InputUtility.updateInputState();
			return true;
		}
		return false;
	}
}
