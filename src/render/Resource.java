package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Resource {
	public static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	public static BufferedImage pikachu,thunder,luffy,naruto,naruto2,narutoBall,reborn,rebornBomb1,rebornBomb2,rebornBomb3,natsu,bleach,bleach2,bgHome,hpbarUnder,hpbarUpper,time,timePoint,mute1,mute2,enter2,center;
	public static BufferedImage[] superAttack = new BufferedImage[6];;
	public static BufferedImage[] bg = new BufferedImage[13];
	public static BufferedImage[] pic = new BufferedImage[6];
	public static AudioClip pikachu1,pikachu2,rebornGun,rebornBomb,fencing,pikaShoot,jump,musicHome,choosePikachu,chooseLuffy,chooseNaruto,chooseNatsu,chooseKurosaki,chooseReborn,buttonSound;
	public static BufferedImage[] button = new BufferedImage[6];
	public static BufferedImage[] choose = new BufferedImage[7];
	public static BufferedImage[] ss = new BufferedImage[6];
	
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
			
			bgHome = ImageIO.read(loader.getResource("res/home.png"));
			bg[0] = ImageIO.read(loader.getResource("res/bg1.png"));
			bg[1] = ImageIO.read(loader.getResource("res/bg2.png"));
			bg[2] = ImageIO.read(loader.getResource("res/bg3.png"));
			bg[3] = ImageIO.read(loader.getResource("res/bg4.png"));
			bg[4] = ImageIO.read(loader.getResource("res/bg5.png"));
			bg[5] = ImageIO.read(loader.getResource("res/bg6.png"));
			bg[6] = ImageIO.read(loader.getResource("res/bg7.png"));
			bg[7] = ImageIO.read(loader.getResource("res/bg8.png"));
			bg[8] = ImageIO.read(loader.getResource("res/bg9.png"));
			bg[9] = ImageIO.read(loader.getResource("res/bg10.png"));
			bg[10] = ImageIO.read(loader.getResource("res/bg11.png"));
			bg[11] = ImageIO.read(loader.getResource("res/bg12.png"));
			bg[12] = ImageIO.read(loader.getResource("res/bg13.png"));
			
			hpbarUnder = ImageIO.read(loader.getResource("res/hpbarUnder-02.png"));
			hpbarUpper = ImageIO.read(loader.getResource("res/hpbarUpper-02.png"));
			time = ImageIO.read(loader.getResource("res/time.png"));
			timePoint = ImageIO.read(loader.getResource("res/timePoint.png"));
			
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
			
			ss[2] = ImageIO.read(loader.getResource("res/ssNaruto.png"));
			
			mute1 = ImageIO.read(loader.getResource("res/mute1.png"));
			mute2 = ImageIO.read(loader.getResource("res/mute2.png"));
			enter2 = ImageIO.read(loader.getResource("res/enter2.png"));
			center = ImageIO.read(loader.getResource("res/center.png"));
			choosePikachu = Applet.newAudioClip((loader.getResource("res/charpikachu.wav")).toURI().toURL());
			chooseLuffy = Applet.newAudioClip((loader.getResource("res/charluffy.wav")).toURI().toURL());
			chooseNaruto = Applet.newAudioClip((loader.getResource("res/charnaruto.wav")).toURI().toURL());
			chooseNatsu = Applet.newAudioClip((loader.getResource("res/charnatsu.wav")).toURI().toURL());
			chooseReborn = Applet.newAudioClip((loader.getResource("res/charreborn.wav")).toURI().toURL());
			chooseKurosaki = Applet.newAudioClip((loader.getResource("res/charichigo.wav")).toURI().toURL());
			musicHome = Applet.newAudioClip((loader.getResource("res/tk_heaven.wav")).toURI().toURL());
			buttonSound = Applet.newAudioClip((loader.getResource("res/button5.wav")).toURI().toURL());
			pikachu1 = Applet.newAudioClip((loader.getResource("res/pika.wav")).toURI().toURL());
			pikachu2 = Applet.newAudioClip((loader.getResource("res/thunder.wav")).toURI().toURL());
			rebornGun = Applet.newAudioClip((loader.getResource("res/reborngun.wav")).toURI().toURL());
			rebornBomb = Applet.newAudioClip((loader.getResource("res/bomb.wav")).toURI().toURL());
			fencing = Applet.newAudioClip((loader.getResource("res/fencing.wav")).toURI().toURL());
			pikaShoot = Applet.newAudioClip((loader.getResource("res/pikashoot.wav")).toURI().toURL());
			jump = Applet.newAudioClip((loader.getResource("res/jump.wav")).toURI().toURL());

			System.out.println("try");
		}catch(Exception e){
			System.out.println("catch");
		}
	}
}
