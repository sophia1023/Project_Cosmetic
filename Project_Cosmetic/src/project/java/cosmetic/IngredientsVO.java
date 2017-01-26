package project.java.cosmetic;

public class IngredientsVO {
	
	private String ingre;
	private int ewg;
	private String function;
	private String use;
	private String effect;
	private String type;
	private int top20;
	
	public IngredientsVO(){}
	
	public IngredientsVO(String ingre, int ewg, String function, String use, String effect, String type, int top20){
		this.ingre = ingre;
		this.ewg = ewg;
		this.function = function;
		this.use = use;
		this.effect = effect;
		this.type = type;
		this.top20 = top20;
	}

	public String getIngre() {
		return ingre;
	}

	public void setIngre(String ingre) {
		this.ingre = ingre;
	}

	public int getEwg() {
		return ewg;
	}

	public void setEwg(int ewg) {
		this.ewg = ewg;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTop20() {
		return top20;
	}

	public void setTop20(int top20) {
		this.top20 = top20;
	}
		
}
