package com.example.demo.validator;

import com.example.demo.dto.AirCompanyCreateDto;
import com.example.demo.dto.AirplaneMigrateDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AirplaneMigrateValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
            return aClass.isAssignableFrom(AirplaneMigrateDto.class);
        }

    @Override
    public void validate(Object o, Errors errors) {
        AirplaneMigrateDto company = (AirplaneMigrateDto) o;
        if (company.getAirCompanyName() == null) {
            errors.rejectValue("name", "is empty", "Field 'name' can not be empty");
        }
        if (company.getAirplaneId()==0) {
            errors.rejectValue("name", "is empty", "Field 'company_type' can not be empty");
        }

    }
}
