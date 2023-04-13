package com.sita.bonus.validation;

import com.sita.bonus.dtos.request.BonusRequest;
import com.sita.bonus.excepction.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class BonusRequestValidator {


    public void postBonusValidation(BonusRequest requests) {

        if (requests.getBonus() == null) {
            throw new BadRequestException("Payload cannot be empty");
        }

        if (requests.getBonus().size() > 1000) {
            throw new BadRequestException("Payload size cannot exceed 1000");
        }

        // Even we can validate much more as per the business requirement.
        // We can also use spring-boot validator for some kind of validation
        // to validate the request like @NotNull @Size and more
    }
}
