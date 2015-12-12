package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import input.InputUtility;

public class How extends JPanel{
	public How() {
		super();
		this.setPreferredSize(new Dimension(640,480));
		this.setBackground(Color.GRAY);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		
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
		
	}

	public boolean update() {
		if(InputUtility.isLeftClickTriggered()){
			InputUtility.updateInputState();
			return true;
		}
		return false;
	}
}
