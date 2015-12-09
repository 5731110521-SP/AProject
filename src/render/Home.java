package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JComponent;

public class Home extends JComponent{
	private boolean isVisible;
	private BufferedImage button;
	public Home(){
		super();
		isVisible=true;
		this.setPreferredSize(new Dimension(640,480));
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		button = Resource.button[0];
		
//		JButton b = new JButton("Start");
//		b.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				InputUtility.mouseLeftDown();
//			}
//		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
					InputUtility.mouseLeftRelease();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
//				button = Resource.button[0];
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
//				int x = e.getX();
//				int y = e.getY();
//				if(x>= 220 && x<=420 && y>=325 && y<=375)
//					button = Resource.button[1];
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if(x>= 220 && x<=420 && y>=325 && y<=375)
					InputUtility.mouseLeftDown();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Resource.bg[1], 0, 0, null);
		g2d.drawImage(button, 220, 325, null);
//		g2d.setColor(ColResource.button[0]or.BLACK);
//		g2d.fillRect(0, 0, 640, 480);
	}

	public boolean update() {
		if(InputUtility.isLeftClickTriggered()){
			isVisible=false;
			return true;
		}
		return false;
	}
	
	public boolean isVisible(){
		return isVisible;
	}

	
}
