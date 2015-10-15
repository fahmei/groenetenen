package be.vdab.valueobjects;

public class PostcodeReeks {

	private Integer vanPostcode, totPostcode;
	private final static int MIN_POSTCODE = 1000;
	private final static int MAX_POSTCODE = 9999;
	
	public boolean bevat(Integer postcode){
		return postcode >= vanPostcode && postcode <= totPostcode;
	}

	public Integer getVanPostcode() {
		return vanPostcode;
	}

	public Integer getTotPostcode() {
		return totPostcode;
	}

	public void setVanPostcode(Integer vanPostcode) {
		valideer(vanPostcode);
		this.vanPostcode = vanPostcode;
	}

	public void setTotPostcode(Integer totPostcode) {
		valideer(totPostcode);
		this.totPostcode = totPostcode;
	}
	
	private void valideer(int postcode){
		if(postcode < MIN_POSTCODE || postcode > MAX_POSTCODE){
			throw new IllegalArgumentException(); 
		}
	}
}
