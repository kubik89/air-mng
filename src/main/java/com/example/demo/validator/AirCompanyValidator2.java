package com.example.demo.validator;

import com.example.demo.dto.AirCompanyCreateDto;
import com.example.demo.entity.AirCompany;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AirCompanyValidator2 implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(AirCompany.class);
        }

    @Override
    public void validate(Object o, Errors errors) {
        AirCompany company = (AirCompany) o;
        if (company.getName().equals("")) {
            errors.rejectValue("name", "is empty", "Field 'name' can not be empty");
        }
        if (company.getCompany_type().equals("")) {
            errors.rejectValue("name", "is empty", "Field 'company_type' can not be empty");
        }

    }
}
