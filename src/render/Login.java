package render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Player;
import input.InputUtility;
import logic.MyException;

public class Login extends JPanel{
	private JTextField tf;
	private JLabel l;
	private String Name;
	public static Player player1,player2;
	private int player;
	
	public Login(int i) {
		super(new GridBagLayout());
		this.setPreferredSize(new Dimension(640,480));
		this.setBackground(Color.GRAY);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		
		Name="";
		l = new JLabel("Enter Player"+i+"'s name : ");
		l.setFont(new Font("Tahoma", 0, 30));
		tf = new JTextField("");
		tf.setFont(new Font("Tahoma", 0, 30));
		tf.setPreferredSize(new Dimension(300, 50));
		player=i;
		
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
		
		KeyListener k = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				InputUtility.setKeyPressed(arg0.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				InputUtility.setKeyPressed(arg0.getKeyCode(), true);
			}
		};
		
		this.addKeyListener(k);
		tf.addKeyListener(k);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Tahoma", 0, 20));
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D r2 = fm.getStringBounds("- Enter -", g2d);
		g2d.drawString("- Enter -", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2+80);
	}

	public boolean update() throws MyException{
		if(InputUtility.isLeftClickTriggered() || InputUtility.getKeyPressed(KeyEvent.VK_ENTER)){
			InputUtility.setKeyPressed(KeyEvent.VK_ENTER, false);
			InputUtility.updateInputState();
				if(tf.getText().equals("")){
					throw new MyException(this,l);
				}else{
//					setVisible(false);
					Name=tf.getText();
					if(player==1){
						player1=new Player(1,Name);
					}else{
						player2=new Player(2,Name);
					}
					return true;
				}
		}
		return false;
		
	}

	public String getName() {
		return Name;
	}
}
