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
	public static BufferedImage naruto2;
	public static BufferedImage narutoBall;
	public static BufferedImage reborn;
	public static BufferedImage rebornBomb1;
	public static BufferedImage rebornBomb2;
	public static BufferedImage rebornBomb3;
	public static BufferedImage natsu;
	public static BufferedImage bleach;
	public static BufferedImage bleach2;
	public static BufferedImage[] superAttack = new BufferedImage[6];;
	public static BufferedImage[] bg = new BufferedImage[2];
	public static BufferedImage[] pic = new BufferedImage[6];
	public static BufferedImage hpbarUnder,hpbarUpper;
	public static BufferedImage mute1,mute2;
	public static AudioClip pikachu1;
	public static AudioClip pikachu2;
	public static AudioClip rebornGun;
	public static AudioClip rebornBomb;
	public static AudioClip fencing;
	public static AudioClip pikaShoot;
	public static AudioClip jump;
	public static AudioClip musicHome;
	public static BufferedImage[] button = new BufferedImage[6];
	public static BufferedImage[] choose = new BufferedImage[7];
	
	static{
		try{
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			pikachu = ImageIO.read(loader.getResource("res/p.gif"));
			thunder = ImageIO.read(loader.getResource("res/thunder.png"));
			
			luffy = ImageIO.read(loader.getResource("res/luffy.png"));
			
			naruto = ImageIO.read(loader.getResource("res/naruto.png"));
			naruto2 = ImageIO.read(loader.getResource("res/naruto2.png"));
			narutoBall = ImageIO.read(loader.getResource("res/narutoBall.png"));
			
			reborn = ImageIO.read(loader.getResource("res/reborn.png"));
			rebornBomb1 = ImageIO.read(loader.getResource("res/reborn2.png"));
			rebornBomb2 = ImageIO.read(loader.getResource("res/reborn3.png"));
			rebornBomb3 = ImageIO.read(loader.getResource("res/reborn4.png"));
			
			natsu = ImageIO.read(loader.getResource("res/Natsu.png"));
			
			bleach = ImageIO.read(loader.getResource("res/bleach.png"));
			bleach2 = ImageIO.read(loader.getResource("res/bleach2.png"));
			
			superAttack[0] = ImageIO.read(loader.getResource("res/pickachu2.png"));
			superAttack[3] = ImageIO.read(loader.getResource("res/reborn.png"));
			
			bg[0]= ImageIO.read(loader.getResource("res/bg-01.png"));
			bg[1]= ImageIO.read(loader.getResource("res/home.png"));
			hpbarUnder = ImageIO.read(loader.getResource("res/hpbarUnder-02.png"));
			hpbarUpper = ImageIO.read(loader.getResource("res/hpbarUpper-02.png"));
			
			pic[0]= ImageIO.read(loader.getResource("res/pikachupic-01.png"));
			pic[1]= ImageIO.read(loader.getResource("res/luffypic-02.png"));
			pic[2] = ImageIO.read(loader.getResource("res/narutopic-03.png"));
			pic[3] = ImageIO.read(loader.getResource("res/rebornpic-04.png"));
			pic[4] = ImageIO.read(loader.getResource("res/natsupic-05.png"));
			pic[5] = ImageIO.read(loader.getResource("res/bleachpic-06.png"));
			
			button[0] = ImageIO.read(loader.getResource("res/bstart.png"));
			button[1] = ImageIO.read(loader.getResource("res/bhow.png"));
			button[2] = ImageIO.read(loader.getResource("res/bSetting.png"));
			button[3] = ImageIO.read(loader.getResource("res/bstart2.png"));
			button[4] = ImageIO.read(loader.getResource("res/bhow2.png"));
			button[5] = ImageIO.read(loader.getResource("res/bSetting2.png"));
			
			choose[6] = ImageIO.read(loader.getResource("res/choose.png"));
			choose[0] = ImageIO.read(loader.getResource("res/choosepi.png"));
			choose[1] = ImageIO.read(loader.getResource("res/chooselu.png"));
			choose[2] = ImageIO.read(loader.getResource("res/choosenar.png"));
			choose[3] = ImageIO.read(loader.getResource("res/choosere.png"));
			choose[4] = ImageIO.read(loader.getResource("res/choosena.png"));
			choose[5] = ImageIO.read(loader.getResource("res/chooseku.png"));
			
			mute1 = ImageIO.read(loader.getResource("res/mute1.png"));
			mute2 = ImageIO.read(loader.getResource("res/mute2.png"));
			
			pikachu1 = Applet.newAudioClip((loader.getResource("res/pika.wav")).toURI().toURL());
			pikachu2 = Applet.newAudioClip((loader.getResource("res/thunder.wav")).toURI().toURL());
			rebornGun = Applet.newAudioClip((loader.getResource("res/reborngun.wav")).toURI().toURL());
			rebornBomb = Applet.newAudioClip((loader.getResource("res/bomb.wav")).toURI().toURL());
			fencing = Applet.newAudioClip((loader.getResource("res/fencing.wav")).toURI().toURL());
			pikaShoot = Applet.newAudioClip((loader.getResource("res/pikashoot.wav")).toURI().toURL());
			jump = Applet.newAudioClip((loader.getResource("res/jump.wav")).toURI().toURL());
			musicHome = Applet.newAudioClip(loader.getResource("res/tk_heaven.wav"));
//			run = Applet.newAudioClip((loader.getResource("res/run.wav")).toURI().toURL());
			System.out.println("try");
		}catch(Exception e){
			System.out.println("catch");
		}
	}
}
