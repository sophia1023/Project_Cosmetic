package project.java.cosmetic;

public interface CosmeticDAO {
	
	public abstract CosmeticVO select_Cos(String name);
	public abstract ImageVO select_image(String name);
	public abstract IngredientsVO select_Ingre(String ingre);
	public abstract int insert_Cos(CosmeticVO cvo, ImageVO civo);
	
	//얘는 일단 보고.....ㅋㅋㅋ 함수가 3개 밖에 안되서 좀 그르네?ㅋㅋㅋ
	public abstract int insert_Ingre(CosmeticVO cvo, IngredientsVO ivo);
	
}
