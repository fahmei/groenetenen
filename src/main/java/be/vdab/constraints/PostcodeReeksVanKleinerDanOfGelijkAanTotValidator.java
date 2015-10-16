package be.vdab.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.valueobjects.PostcodeReeks;

public class PostcodeReeksVanKleinerDanOfGelijkAanTotValidator
		implements ConstraintValidator<PostcodeReeksVanKleinerDanOfGelijkAanTot, PostcodeReeks> {

	@Override
	public void initialize(PostcodeReeksVanKleinerDanOfGelijkAanTot constraintAnnotation) {}

	@Override
	public boolean isValid(PostcodeReeks reeks, ConstraintValidatorContext context) {
		if(reeks.getVanPostcode() == null || reeks.getTotPostcode() == null){
			return true;
		}
		return reeks.getVanPostcode() <= reeks.getTotPostcode();
	}

}
