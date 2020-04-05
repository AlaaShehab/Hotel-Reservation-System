package Controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    static boolean validEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email);
        if(m.find() && m.group().equals(email)){
            return true;
        }
        return false;
    }

    static boolean validEmail(TextField email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if(m.find() && m.group().equals(email.getText())){
            return true;
        }
        return false;
    }

    static boolean validName(String name) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(name);
        if(m.find() && m.group().equals(name)){
            return true;
        }
        return false;
    }

    static boolean validName(TextField name) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(name.getText());
        if(m.find() && m.group().equals(name.getText())){
            return true;
        }
        return false;
    }

    static boolean validPassword (TextField password) {
        //TODO extra validation
        return !password.getText().isEmpty();
    }

    static boolean validPassword (PasswordField password) {
        //TODO extra validation
        return !password.getText().isEmpty();
    }

    static boolean validateMobileNo(TextField phone){
        Pattern p = Pattern.compile("(01)[0-3][0-9]{8}");
        Matcher m = p.matcher((String)phone.getText());
        if(m.find() && m.group().equals((String)phone.getText())){
            return true;
        }
        return false;
    }
}
