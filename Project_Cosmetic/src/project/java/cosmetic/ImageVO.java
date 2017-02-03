package project.java.cosmetic;

import java.awt.image.BufferedImage;

public class ImageVO {
	
	public String cosmetic;
	public BufferedImage img;
	
	public ImageVO(){}
	
	public ImageVO(String cosmetic, BufferedImage img){
		this.cosmetic = cosmetic;
		this.img= img;
	}

	public String getCosmetic() {
		return cosmetic;
	}

	public void setCosmetic(String cosmetic) {
		this.cosmetic = cosmetic;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "☆ 화장품 명 : " + cosmetic + "-------------"+ img;
	}
}
