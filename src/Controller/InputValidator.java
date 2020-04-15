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
        return !password.getText().isEmpty();
    }

    static boolean validPassword (PasswordField password) {
        return !password.getText().isEmpty();
    }

    static boolean validMobileNo(TextField phone){
        Pattern p = Pattern.compile("(01)[0-3][0-9]{8}");
        Matcher m = p.matcher(phone.getText());
        if(m.find() && m.group().equals(phone.getText())){
            return true;
        }
        return false;
    }

    static boolean validDate(TextField date){
        Pattern p = Pattern.compile("(0[1-9]|[12]\\d|3[01])\\/(0[1-9]|1[0-2])\\/(2020)");
        Matcher m = p.matcher(date.getText());
        if(m.find() && m.group().equals(date.getText())){
            return true;
        }
        return false;
    }

    static boolean validCardNumber (TextField cardNumber) {
        Pattern creditPatern = Pattern.compile("[0-9]{16}");
        Matcher creditMatch = creditPatern.matcher(cardNumber.getText());
        if(!creditMatch.find() || !creditMatch.group().equals(cardNumber.getText())){
            return false;
        }
        return true;
    }

    static boolean validCVV (TextField securityNo) {
        Pattern securityPatern = Pattern.compile("[0-9]{4}");
        Matcher securityMatch = securityPatern.matcher(securityNo.getText());
        if(!securityMatch.find() || !securityMatch.group().equals(securityNo.getText())){
            return false;
        }
        return true;
    }

    static boolean validExpirationDate (TextField expDate) {
        Pattern expPatern = Pattern.compile("^(0[1-9]|1[0-2])\\/?([0-9]{2})$");
        Matcher expMatch = expPatern.matcher(expDate.getText());
        if(!expMatch.find() || !expMatch.group().equals(expDate.getText())){
            return false;
        }
        return true;
    }

    public static boolean validFilterNumber(TextField searchNumber) {
        if (searchNumber.getText().isEmpty()) {
            return true;
        }
        Pattern p = Pattern.compile("[0-9]");
        Matcher matcher = p.matcher(searchNumber.getText());
        if(!matcher.find() || !matcher.group().equals(searchNumber.getText())){
            return false;
        }
        return true;
    }

    public static boolean validPriceRange(TextField minPrice, TextField maxPrice) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher matcher = p.matcher(minPrice.getText());
        if(!matcher.find() || !matcher.group().equals(maxPrice.getText())){
            return false;
        }
        matcher = p.matcher(maxPrice.getText());
        if(!matcher.find() || !matcher.group().equals(maxPrice.getText())){
            return false;
        }

        int min = minPrice.getText().isEmpty() ? 0 : Integer.parseInt(minPrice.getText());
        int max = maxPrice.getText().isEmpty() ? 0 : Integer.parseInt(maxPrice.getText());

        if (min < 0 || max < 0 || max < min) {
            return false;
        }
        return true;
    }

    public static boolean validDateRange(TextField checkInDate, TextField checkOutDate) {
        String[] checkIn = checkInDate.getText().split("/");
        String[] checkOut = checkOutDate.getText().split("/");

        if ((Integer.parseInt(checkIn[1]) == Integer.parseInt(checkOut[1])
                && Integer.parseInt(checkIn[0]) > Integer.parseInt(checkOut[0]))
                || Integer.parseInt(checkIn[1]) > Integer.parseInt(checkOut[1])) {
            return false;
        }
        return true;
    }

    public static boolean validInteger(TextField number) {
        Pattern p = Pattern.compile("^([1-9])([0-9]*)");
        Matcher matcher = p.matcher(number.getText());
        if(!matcher.find() || !matcher.group().equals(number.getText())){
            return false;
        }
    }
}
