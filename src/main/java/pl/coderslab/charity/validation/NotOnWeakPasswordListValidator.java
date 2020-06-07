package pl.coderslab.charity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NotOnWeakPasswordListValidator implements ConstraintValidator<NotOnWeakPasswordList, String> {
    public void initialize(NotOnWeakPasswordList constraint) {
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {

        URL res = getClass().getClassLoader().getResource("validation/weak_passwords_list.txt");
        try {
            List<String> weakPasswordsList = Files.readAllLines(Paths.get(res.toURI()));
            if (weakPasswordsList.contains(obj)) {
                return false;
            } else {
                return true;
            }

        } catch (URISyntaxException | IOException e) {
            return true;
        }
    }
}
