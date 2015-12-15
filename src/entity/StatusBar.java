package entity;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import render.IRenderable;
import render.Resource;

public class StatusBar implements IRenderable{
	private character.Character c1,c2;
	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	
	public StatusBar(character.Character c1, character.Character c2){
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public void draw(Graphics2D g) {
		
		//CharacterPic 100x35
				BufferedImage c1Pic = Resource.pic[c1.indexC];
				g.drawImage(c1Pic,9,0,123, 60,null);
				
				BufferedImage c2Pic = Resource.pic[c2.indexC];
				AffineTransform at = new AffineTransform();
					at = AffineTransform.getScaleInstance(-1, 1);
					at.translate(-c2Pic.getWidth(null), 0);
					AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
					c2Pic = op.filter(c2Pic, null);
				g.drawImage(c2Pic,520,0,121,60,null);
		
		//hpbarUnder
		g.drawImage(Resource.hpbarUnder, 6-3, 55+3,296,21, null);
		at = new AffineTransform();
		at = AffineTransform.getScaleInstance(-1, 1);
		at.translate(-Resource.hpbarUnder.getWidth(null), 0);
		op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		BufferedImage image = op.filter(Resource.hpbarUnder, null);
		g.drawImage(image , 338+3, 55+3,296,21, null);
		
		//HpBar 290x21
		g.setComposite(transcluentWhite);
		g.setColor(Color.RED);
		g.fillRect(9, 56, (int)(c1.getHealthPoint()/100.0*290), 20);
		g.fillRect(341+290-(int)(c2.getHealthPoint()/100.0*290), 56, (int)(c2.getHealthPoint()/100.0*290), 20);
		g.setComposite(opaque);
		
		//hpbarUpper
		g.drawImage(Resource.hpbarUpper, 6, 55,296,21, null);
		image = op.filter(Resource.hpbarUpper, null);
		g.drawImage(image, 338, 55,296,21, null);
		
		//powerMax
		g.setColor(Color.GRAY);
		if(c1.getPowerCount()>=1) g.setColor(new Color(252, 233, 169));
		g.fillOval(15, 67, 12, 12);
		g.setColor(Color.GRAY);
		if(c1.getPowerCount()>=2) g.setColor(new Color(251, 229, 155));
		g.fillOval(40, 67,12, 12);
		g.setColor(Color.GRAY);
		if(c1.getPowerCount()>=3) g.setColor(new Color(250, 200, 78));
		g.fillOval(65, 67, 12, 12);
		g.setColor(Color.GRAY);
		if(c1.getPowerCount()>=4) g.setColor(new Color(253, 191, 41));
		g.fillOval(90, 67, 12, 12);
		
		g.setColor(Color.GRAY);
		if(c2.getPowerCount()>=1) g.setColor(new Color(176, 222, 252));
		g.fillOval(613, 67, 12, 12);
		g.setColor(Color.GRAY);
		if(c2.getPowerCount()>=2) g.setColor(new Color(144, 209, 251));
		g.fillOval(588, 67, 12, 12);
		g.setColor(Color.GRAY);
		if(c2.getPowerCount()>=3) g.setColor(new Color(104, 194, 252));
		g.fillOval(563, 67, 12, 12);
		g.setColor(Color.GRAY);
		if(c2.getPowerCount()>=4) g.setColor(new Color(67, 180, 252));
		g.fillOval(538, 67, 12, 12);
		
		//time
		g.drawImage(Resource.time, 0, 3, null);
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getZ() {
		return 99999;
	}

	@Override
	public void update() {
		
	}

	@Override
	public boolean getFlashing() {
		return false;
	}
}
