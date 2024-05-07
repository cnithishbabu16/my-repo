package org.revhire.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialsValidator {
    public static boolean isValidUsername(String username){
        String usernameRegex="[a-zA-Z]+[0-9]+";
        Pattern pattern = Pattern.compile(usernameRegex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    public static boolean isValidEmail(String email)
    {
        String emailRegex = "[a-z][a-zA-Z0-9]+@[a-zA-Z]+[.]com";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password)
    {
        String pswd="(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#$%^&*]).{6,}";
        Pattern pattern = Pattern.compile(pswd);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
