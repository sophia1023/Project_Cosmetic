package project.java.cosmetic;

public class CosmeticVO {
	
	private String cos_Name;
	private String cos_Com;
	private String cos_Cate;
	private String cos_Ingre;
	public CosmeticVO(){}
	
	public CosmeticVO(String cos_Name, String cos_Com, String cos_Cate, String cos_Ingre){
		this.cos_Name = cos_Name;
		this.cos_Com = cos_Com;
		this.cos_Cate = cos_Cate;
		this.cos_Ingre = cos_Ingre;
	}

	public String getCos_Name() {
		return cos_Name;
	}

	public void setCos_Name(String cos_Name) {
		this.cos_Name = cos_Name;
	}

	public String getCos_Com() {
		return cos_Com;
	}

	public void setCos_Com(String cos_Com) {
		this.cos_Com = cos_Com;
	}

	public String getCos_Cate() {
		return cos_Cate;
	}

	public void setCos_Cate(String cos_Cate) {
		this.cos_Cate = cos_Cate;
	}

	public String getCos_Ingre() {
		return cos_Ingre;
	}

	public void setCos_Ingre(String cos_Ingre) {
		this.cos_Ingre = cos_Ingre;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "☆ 화장품 명 : " + cos_Name + "\n ☆ 회사 명 : " + cos_Com + "\n ☆ 카테고리 : " + cos_Cate;
	}

	
}
