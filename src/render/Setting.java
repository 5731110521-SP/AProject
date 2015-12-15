package render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import character.Pikachu;
import character.Reborn;
import input.InputUtility;

public class Setting extends JPanel{
	private static final Font FONT = new Font("Century Gothic", Font.BOLD, 20);
	BufferedImage image1,image2;
	private int change;
	private JPanel left1,right1,jump1,attack1,shoot1,superAttack1,left2,right2,jump2,attack2,shoot2,superAttack2;
	public static int[] key;
	private JLabel[] l;
	private JLabel player1;
	private JLabel player2;
	private JLabel setting;
	private int index;
	static{
		key[0]=KeyEvent.VK_A;
		key[1]=KeyEvent.VK_D;
		key[2]=KeyEvent.VK_W;
		key[3]=KeyEvent.VK_F;
		key[4]=KeyEvent.VK_X;
		key[5]=KeyEvent.VK_Z;
		key[6]=KeyEvent.VK_LEFT;
		key[7]=KeyEvent.VK_RIGHT;
		key[8]=KeyEvent.VK_UP;
		key[9]=KeyEvent.VK_DOWN;
		key[10]=KeyEvent.VK_ENTER;
		key[11]=KeyEvent.VK_CONTROL;
	}
	
	public Setting() {
		super(new BorderLayout());
		image1 = Resource.mute1;
		image2 = Resource.mute2;
		change = 0;
		index = -1;
		left1 = new JPanel();
		right1 = new JPanel();
		jump1 = new JPanel();
		attack1 = new JPanel();
		shoot1 = new JPanel();
		superAttack1 = new JPanel();
		left2 = new JPanel();
		right2 = new JPanel();
		jump2 = new JPanel();
		attack2 = new JPanel();
		shoot2 = new JPanel();
		superAttack2 = new JPanel();
		key = new int[12];
		l = new JLabel[12];
		player1 = new JLabel("Player1");
		player2 = new JLabel("Player2");
		setting = new JLabel("SETTING");
		
		this.setPreferredSize(new Dimension(640,480));
		setting.setFont(new Font("Broadway",Font.PLAIN,50));
		setting.setForeground(Color.WHITE);
		player1.setFont(FONT);
		player1.setForeground(Color.BLACK);
		player2.setFont(FONT);
		player2.setForeground(Color.BLACK);
		JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
		north.setPreferredSize(new Dimension(640,80));
		north.setBackground(Color.DARK_GRAY);
		north.add(setting);
		north.setVisible(true);
		this.add(north,BorderLayout.NORTH);

		JPanel center = new JPanel(new GridLayout(4,7,5,5));
		center.setPreferredSize(new Dimension(640,380));
		center.add(new Panel());
		
		JLabel l1 = new JLabel("LEFT");
		l1.setFont(FONT);
		JPanel f1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		f1.add(l1);
		center.add(f1);
		
		JLabel l2 = new JLabel("RIGHT");
		l2.setFont(FONT);
		JPanel f2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		f2.add(l2);
		center.add(f2);
		
		JLabel l3 = new JLabel("JUMP");
		l3.setFont(FONT);
		JPanel f3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		f3.add(l3);
		center.add(f3);
		
		JLabel l4 = new JLabel("ATTACK");
		l4.setFont(FONT);
		JPanel f4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		f4.add(l4);
		center.add(f4);
		
		JLabel l5 = new JLabel("SHOOT");
		l5.setFont(FONT);
		JPanel f5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		f5.add(l5);
		center.add(f5);
		
		JLabel l6 = new JLabel("SUPERATTACK");
		l6.setFont(FONT);
		JPanel f6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		f6.add(l6);
		center.add(f6);
		
		center.add(player1);
		center.add(left1);
		center.add(right1);
		center.add(jump1);
		center.add(attack1);
		center.add(shoot1);
		center.add(superAttack1);
		center.add(player2);
		center.add(left2);
		center.add(right2);
		center.add(jump2);
		center.add(attack2);
		center.add(shoot2);
		center.add(superAttack2);
		
		center.add(new JPanel());
		center.add(new JPanel());
		center.add(new JPanel());
		center.add(new JPanel());
		center.add(new JPanel());
		center.add(new JPanel());
		center.add(new JPanel());
		
		
		this.add(center,BorderLayout.CENTER);
		
		ImageIcon o = new ImageIcon();
		o.setImage(image1);
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER,240,0));
		JLabel i = new JLabel();
		i.setIcon(o);
		i.addMouseListener(new MouseAdapter() {		
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Resource.buttonSound.play();
				if(change == 0){
					o.setImage(image2);
					change = 1;
					Resource.musicHome.stop();
				}else {
					o.setImage(image1);
					change = 0;
					Resource.musicHome.play();
				}
			}
		});
		
		JLabel j = new JLabel("Back");
		j.setFont(FONT);
		south.add(i);
		south.add(j);
		this.add(south,BorderLayout.SOUTH);
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		
		l[0]=new JLabel();
		l[1]=new JLabel();
		l[2]=new JLabel();
		l[3]=new JLabel();
		l[4]=new JLabel();
		l[5]=new JLabel();
		l[6]=new JLabel();
		l[7]=new JLabel();
		l[8]=new JLabel();
		l[9]=new JLabel();
		l[10]=new JLabel();
		l[11]=new JLabel();
		
		left1.setPreferredSize(new Dimension(100,100));
		left1.setBackground(Color.BLACK);
		l[0].setText(KeyEvent.getKeyText(key[0]));
		l[0].setFont(FONT);
		l[0].setForeground(Color.WHITE);
		left1.add(l[0]);
		left1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				left1.setBackground(Color.BLACK);
				l[0].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				left1.setBackground(Color.LIGHT_GRAY);
				l[0].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = 0;
			}
		});
		
		right1.setPreferredSize(new Dimension(100,100));
		right1.setBackground(Color.BLACK);
		l[1].setText(KeyEvent.getKeyText(key[1]));
		l[1].setFont(FONT);
		l[1].setForeground(Color.WHITE);
		right1.add(l[1]);
		right1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent arg0) {
				right1.setBackground(Color.BLACK);
				l[1].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				right1.setBackground(Color.LIGHT_GRAY);
				l[1].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index = 1;
			}
		});
		
		jump1.setPreferredSize(new Dimension(100,100));
		jump1.setBackground(Color.BLACK);
		l[2].setText(KeyEvent.getKeyText(key[2]));
		l[2].setFont(FONT);
		l[2].setForeground(Color.WHITE);
		jump1.add(l[2]);
		jump1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				jump1.setBackground(Color.BLACK);
				l[2].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				jump1.setBackground(Color.LIGHT_GRAY);
				l[2].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 2;
			}
		});
		
		attack1.setPreferredSize(new Dimension(100,100));
		attack1.setBackground(Color.BLACK);
		l[3].setText(KeyEvent.getKeyText(key[3]));
		l[3].setFont(FONT);
		l[3].setForeground(Color.WHITE);
		attack1.add(l[3]);
		attack1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				attack1.setBackground(Color.BLACK);
				l[3].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				attack1.setBackground(Color.LIGHT_GRAY);
				l[3].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 3;
			}
		});
		
		shoot1.setPreferredSize(new Dimension(100,100));
		shoot1.setBackground(Color.BLACK);
		l[4].setText(KeyEvent.getKeyText(key[4]));
		l[4].setFont(FONT);
		l[4].setForeground(Color.WHITE);
		shoot1.add(l[4]);
		shoot1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				shoot1.setBackground(Color.BLACK);
				l[4].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				shoot1.setBackground(Color.LIGHT_GRAY);
				l[4].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 4 ;
			}
		});
		
		superAttack1.setPreferredSize(new Dimension(100,100));
		superAttack1.setBackground(Color.BLACK);
		l[5].setText(KeyEvent.getKeyText(key[5]));
		l[5].setFont(FONT);
		l[5].setForeground(Color.WHITE);
		superAttack1.add(l[5]);
		superAttack1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				superAttack1.setBackground(Color.BLACK);
				l[5].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				superAttack1.setBackground(Color.LIGHT_GRAY);;
				l[5].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 5;
			}
		});
		
		left2.setPreferredSize(new Dimension(100,100));
		left2.setBackground(Color.BLACK);
		l[6].setText(KeyEvent.getKeyText(key[6]));
		l[6].setFont(FONT);
		l[6].setForeground(Color.WHITE);
		left2.add(l[6]);
		left2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				left2.setBackground(Color.BLACK);
				l[6].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				left2.setBackground(Color.LIGHT_GRAY);
				l[6].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 6;
			}
		});
		
		right2.setPreferredSize(new Dimension(100,100));
		right2.setBackground(Color.BLACK);
		l[7].setText(KeyEvent.getKeyText(key[7]));
		l[7].setFont(FONT);
		l[7].setForeground(Color.WHITE);
		right2.add(l[7]);
		right2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				right2.setBackground(Color.BLACK);
				l[7].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				right2.setBackground(Color.LIGHT_GRAY);
				l[7].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 7;
			}
		});
		
		jump2.setPreferredSize(new Dimension(100,1000));
		jump2.setBackground(Color.BLACK);
		l[8].setText(KeyEvent.getKeyText(key[8]));
		l[8].setFont(FONT);
		l[8].setForeground(Color.WHITE);
		jump2.add(l[8]);
		jump2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				jump2.setBackground(Color.BLACK);
				l[8].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				jump2.setBackground(Color.LIGHT_GRAY);
				l[8].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 8;
			}
		});
		
		attack2.setPreferredSize(new Dimension(100,100));
		attack2.setBackground(Color.BLACK);
		l[9].setText(KeyEvent.getKeyText(key[9]));
		l[9].setFont(FONT);
		l[9].setForeground(Color.WHITE);
		attack2.add(l[9]);
		attack2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				attack2.setBackground(Color.BLACK);
				l[9].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				attack2.setBackground(Color.LIGHT_GRAY);
				l[9].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 9;
			}
		});
		
		shoot2.setPreferredSize(new Dimension(100,100));
		shoot2.setBackground(Color.BLACK);
		l[10].setText(KeyEvent.getKeyText(key[10]));
		l[10].setFont(FONT);
		l[10].setForeground(Color.WHITE);
		shoot2.add(l[10]);
		shoot2.addMouseListener(new MouseAdapter() {
	
			@Override
			public void mouseExited(MouseEvent e) {
				shoot2.setBackground(Color.BLACK);
				l[10].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				shoot2.setBackground(Color.LIGHT_GRAY);
				l[10].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 10;
			}
		});
		
		superAttack2.setPreferredSize(new Dimension(100,100));
		superAttack2.setBackground(Color.BLACK);
		l[11].setText(KeyEvent.getKeyText(key[11]));
		l[11].setFont(FONT);
		l[11].setForeground(Color.WHITE);
		superAttack2.add(l[11]);
		superAttack2.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				superAttack2.setBackground(Color.BLACK);
				l[11].setForeground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				superAttack2.setBackground(Color.LIGHT_GRAY);
				l[11].setForeground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 11;
			}
		});
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				for(int i=0;i<key.length;i++){
					if(index==i){
						key[i] = e.getKeyCode();
						l[i].setText(KeyEvent.getKeyText(key[i]));
						break;
					}
				}
				index=-1;
 			}
		});
		
		
		
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
				InputUtility.mouseLeftDown();
				
			}
		});
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(FONT);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Back", 580, 470);
	}

	public boolean update() {
		if(InputUtility.isLeftClickTriggered()){
			InputUtility.updateInputState();
			return true;
		}
		return false;
	}
	

}
