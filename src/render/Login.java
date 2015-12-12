package render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import input.InputUtility;
import logic.MyException;

public class Login extends JPanel{
	JTextField tf;
	JLabel l;
	private String Name;
	public Login(int i) {
		super(new GridBagLayout());
		this.setPreferredSize(new Dimension(640,480));
		this.setBackground(Color.GRAY);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		
		Name="";
		l = new JLabel("Enter Player"+i+"'s name : ");
		l.setFont(new Font("System", 0, 30));
		tf = new JTextField("");
		tf.setFont(new Font("System", 0, 30));
		tf.setPreferredSize(new Dimension(300, 50));
		
		add(l);
		add(tf);
		
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
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Tahoma", 0, 20));
		g2d.drawString("- Enter -", GameScreen.width/2-40, GameScreen.height/2+80);
	}

	public boolean update() throws MyException{
		if(InputUtility.isLeftClickTriggered()){
			InputUtility.updateInputState();
				if(tf.getText().equals("")){
					throw new MyException(this,l);
				}else{
//					setVisible(false);
					Name=tf.getText();
					return true;
				}
		}
		return false;
		
	}

	public String getName() {
		return Name;
	}
}
