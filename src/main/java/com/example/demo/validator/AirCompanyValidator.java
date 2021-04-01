package com.example.demo.validator;

import com.example.demo.dto.AirCompanyCreateDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AirCompanyValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(AirCompanyCreateDto.class);
        }

    @Override
    public void validate(Object o, Errors errors) {
        AirCompanyCreateDto company = (AirCompanyCreateDto) o;
        if (company.getName().equals("")) {
            errors.rejectValue("name", "is empty", "Field 'name' can not be empty");
        }
        if (company.getCompany_type().equals("")) {
            errors.rejectValue("name", "is empty", "Field 'company_type' can not be empty");
        }

    }
}
