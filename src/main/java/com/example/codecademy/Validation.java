package com.example.codecademy;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Validation {

    @FXML
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    public static boolean validateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    // geboortedatum doet joshua
    public static boolean validateDateOfBirth(String dob){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        try {
            dateFormatter.parse(dob);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    // percentage moet tussle 1-100 mohamed
    public static boolean validatePercentage(Integer percnt){
        String regex = "^[0-9]$|^[1-9][0-9]$|^(100)$";
        Pattern ptrn = Pattern.compile(regex);
        return ptrn.matcher(String.valueOf(percnt)).matches();
    }



    //certificaat cijfer tussen 1 en 10 joshua
    public static boolean validateCertificaatCijfer(Integer cijfer){
        String regex = "^(?:[1-9]|0[1-9]|10)$";
        Pattern ptrn = Pattern.compile(regex);
        return ptrn.matcher(String.valueOf(cijfer)).matches();
    }

    //postcode mohamed postcode voorbeeld 3088 BF, 2455 GK   (eerste nummer niet 0)
    public static boolean validatePostCode(String postCode){
        String postCodeRegex ="^[0-9]{4}[a-zA-Z]{2}$"; // pattern schrijven  3088 BF(eerste nummer mag niet 0 zijn)
        Pattern pat = Pattern.compile(postCodeRegex);
        if (postCode == null){
            return false;
        }
        postCode = postCode.replace(" ","");
        System.out.println(postCode);
        return pat.matcher(postCode).matches();
    }


}
