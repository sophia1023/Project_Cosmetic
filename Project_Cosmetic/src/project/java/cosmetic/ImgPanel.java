package project.java.cosmetic;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class ImgPanel extends JPanel {

	BufferedImage bi;
	BufferedImage reBi;
	ImageIcon imgIcon;
	
	ImgPanel() {
	}

	/** 현재 프로젝트안에 저장된 이미지를 읽어서 BufferedImage를 생성한다 */
	public void setImage(String image) {

		try {
			File file = new File(image);
			bi = ImageIO.read(file);
			reBi = createResized(bi,290,393,true);
		    repaint();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void setImage2(BufferedImage bi) throws IOException {
		reBi = createResized(bi,290,393,true);
	}
	
	public BufferedImage createResized(Image origin, int width, int height, boolean alpha){
		int imgtype = alpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		
		BufferedImage scale = new BufferedImage(width,height, imgtype);
		Graphics2D g = scale.createGraphics();
		if(alpha){
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(bi,0,0,width,height,null);
		g.dispose();
		return scale;
	}

	/** 화면을 다시 그릴 때 자동으로 호출됨. 화면에 BufferedImage의 내용을 그린다 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(reBi,null, 0, 0);
	}
}
