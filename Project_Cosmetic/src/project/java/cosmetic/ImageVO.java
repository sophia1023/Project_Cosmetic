package project.java.cosmetic;

public class ImageVO {
	
	private String cos_Name;
	private String file_Name;
	private byte cos_Image;

	public ImageVO(){}
	
	public ImageVO(String cos_Name, String file_Name, byte cos_Image){
		this.cos_Name = cos_Name;
		this.file_Name = file_Name;
		this.cos_Image = cos_Image;
	}

	public String getCos_Name() {
		return cos_Name;
	}

	public void setCos_Name(String cos_Name) {
		this.cos_Name = cos_Name;
	}

	public String getFile_Name() {
		return file_Name;
	}

	public void setFile_Name(String file_Name) {
		this.file_Name = file_Name;
	}

	public byte getCos_Image() {
		return cos_Image;
	}

	public void setCos_Image(byte cos_Image) {
		this.cos_Image = cos_Image;
	}
	
	
}
