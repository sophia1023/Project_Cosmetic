package project.java.cosmetic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public interface CosmeticDAO {
	
	public abstract CosmeticVO select_Cos(String name);
	public abstract BufferedImage select_cosImge(String name) throws Exception;
	public abstract ArrayList<IngredientsVO> select_Ingre(String[] ingre);
	public abstract int insert_Cos(CosmeticVO cvo);
	public abstract int insert_CosImge(CosmeticVO cvo,String path, String fileName) throws Exception;
		
	//얘는 일단 보고.....ㅋㅋㅋ 함수가 3개 밖에 안되서 좀 그르네?ㅋㅋㅋ
	public abstract int insert_Ingre(CosmeticVO cvo, IngredientsVO ivo);
	
}
