package com.nf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraintAnnotation) {
        System.out.println("这是初始化哦");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null|| !(Pattern.compile("^1[3,5,6,7,8]\\d{9}$").matcher(value).matches())){
            return false;
        }
        return true;
    }
}
