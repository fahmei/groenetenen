package be.vdab.valueobjects;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

@PostcodeReeksVanKleinerDanOfGelijkAanTot
public class PostcodeReeks {

	@NotNull @Postcode
	private Integer vanPostcode, totPostcode;
	
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
		this.vanPostcode = vanPostcode;
	}

	public void setTotPostcode(Integer totPostcode) {
		this.totPostcode = totPostcode;
	}
	
}
