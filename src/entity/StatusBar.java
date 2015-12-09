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
		
		//hpbarUnder
//		g.drawImage(Resource.hpbarUnder, 6-3, 49+3,296,21, null);
		AffineTransform at = new AffineTransform();
		at = AffineTransform.getScaleInstance(-1, 1);
//		at.translate(-Resource.hpbarUnder.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
//		BufferedImage image = op.filter(Resource.hpbarUnder, null);
//		g.drawImage(image , 338+3, 49+3,296,21, null);
		
		//HpBar 290x21
		g.setComposite(transcluentWhite);
		g.setColor(Color.RED);
		g.fillRect(9, 50, (int)(c1.getHealthPoint()/100.0*290), 20);
		g.fillRect(341+290-(int)(c2.getHealthPoint()/100.0*290), 50, (int)(c2.getHealthPoint()/100.0*290), 20);
		g.setComposite(opaque);
		
		//hpbarUpper
//		g.drawImage(Resource.hpbarUpper, 6, 49,296,21, null);
//		image = op.filter(Resource.hpbarUpper, null);
//		g.drawImage(image, 338, 49,296,21, null);
		
		
		//CharacterPic 100x35
		BufferedImage c1Pic = Resource.pic[c1.indexC];
		g.drawImage(c1Pic,9,9,100, 35,null);
		
		BufferedImage c2Pic = Resource.pic[c2.indexC];
		at = new AffineTransform();
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-c2Pic.getWidth(null), 0);
			op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			c2Pic = op.filter(c2Pic, null);
		g.drawImage(c2Pic,531,9,100, 35,null);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 99999;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getFlashing() {
		// TODO Auto-generated method stub
		return false;
	}
}
