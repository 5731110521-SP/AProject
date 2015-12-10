package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Resource {
	public static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	public static BufferedImage pikachu;
	public static BufferedImage thunder;
	public static BufferedImage luffy;
	public static BufferedImage naruto;
	public static BufferedImage narutoBall;
	public static BufferedImage reborn;
	public static BufferedImage natsu;
	public static BufferedImage bleach;
	public static BufferedImage[] bg = new BufferedImage[2];
	public static BufferedImage[] pic = new BufferedImage[6];
	public static BufferedImage hpbarUnder,hpbarUpper;
	public static AudioClip coinSound;
	public static BufferedImage[] button = new BufferedImage[2];
	
	static{
		try{
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			pikachu = ImageIO.read(loader.getResource("res/p.gif"));
			thunder = ImageIO.read(loader.getResource("res/thunder.png"));
			
			luffy = ImageIO.read(loader.getResource("res/luffy.png"));
			
			naruto = ImageIO.read(loader.getResource("res/naruto.png"));
			narutoBall = ImageIO.read(loader.getResource("res/narutoBall.png"));
			
			reborn = ImageIO.read(loader.getResource("res/reborn.png"));
			
			natsu = ImageIO.read(loader.getResource("res/Natsu.png"));
			
			bleach = ImageIO.read(loader.getResource("res/bleach.png"));
			
			bg[0]= ImageIO.read(loader.getResource("res/bg-01.png"));
			bg[1]= ImageIO.read(loader.getResource("res/homebg.png"));
			hpbarUnder = ImageIO.read(loader.getResource("res/hpbarUnder-02.png"));
			hpbarUpper = ImageIO.read(loader.getResource("res/hpbarUpper-02.png"));
			
			pic[0]= ImageIO.read(loader.getResource("res/pikachupic-01.png"));
			pic[1]= ImageIO.read(loader.getResource("res/luffypic-02.png"));
			pic[2] = ImageIO.read(loader.getResource("res/narutopic-03.png"));
			pic[3] = ImageIO.read(loader.getResource("res/rebornpic-04.png"));
			pic[4] = ImageIO.read(loader.getResource("res/natsupic-05.png"));
			pic[5] = ImageIO.read(loader.getResource("res/bleachpic-06.png"));
			button[0] = ImageIO.read(loader.getResource("res/b1.png"));
			button[1] = ImageIO.read(loader.getResource("res/b2.png"));
			
			System.out.println("try");
		}catch(Exception e){
			pikachu = null;
			coinSound = null;
			System.out.println("catch");
		}
	}
}
