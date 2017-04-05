package za.co.mwongho.mapstruct.dto;

public class ProductDto {
	private long code;
	private String name;
	private String description;
	
	public ProductDto() {

	}

	public ProductDto(long code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}
	
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
