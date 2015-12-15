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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel{
	private BufferedImage[] button;
	private int next;
	public Home(){
		super();
		this.setPreferredSize(new Dimension(640,480));
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		if(!Setting.isMute)Resource.musicHome.loop();
		button = new BufferedImage[3];
		next=-1;
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				InputUtility.mouseLeftRelease();
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getX()>=199 &&arg0.getX()<=199+238 && arg0.getY()>=247 && arg0.getY()<= 247+90){
					next=0;
					InputUtility.mouseLeftDown();
					Resource.buttonSound.play();
				}else if(arg0.getX()>=221 &&arg0.getX()<=221+202 && arg0.getY()>=343 && arg0.getY()<= 343+67){
					next=2;
					InputUtility.mouseLeftDown();
					Resource.buttonSound.play();
				}
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(arg0.getX()>=199 &&arg0.getX()<=199+238 && arg0.getY()>=247 && arg0.getY()<= 247+90){
					button[0]=Resource.button[3];
				}else{
					button[0]=Resource.button[0];
				}
				if(arg0.getX()>=221 &&arg0.getX()<=221+202 && arg0.getY()>=343 && arg0.getY()<= 343+67){
					button[2]=Resource.button[5];
				}else{
					button[2]=Resource.button[2];
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
			
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Resource.bgHome, 0, 0, null);
		g2d.drawImage(button[0], 199, 247, null);
		g2d.drawImage(button[2], 221, 343, null);
	}

	public int update() {
		if(InputUtility.isLeftClickTriggered()){
			InputUtility.updateInputState();
			return next;
		}
		return -1;
	}

	
}
