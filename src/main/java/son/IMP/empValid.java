package son.IMP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import son.Model.empEntity;
import son.Service.empService;

import java.util.Date;

/**
 * Created by ngocson on 06/10/2017.
 */
@Component
public class empValid implements Validator {

    @Autowired
    private empService empService;

    public boolean supports(Class<?> aClass) {
        return aClass == empEntity.class;
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"date","empEntity.date");
        empEntity empEntity = (son.Model.empEntity) target;

    }
}
