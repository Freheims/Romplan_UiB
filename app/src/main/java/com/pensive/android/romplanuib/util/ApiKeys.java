package com.pensive.android.romplanuib.util;

/**
 * @author Edvard P. BjÃ¸rgen
 * @version 1.0
 */

public class ApiKeys {

    public String getApiKey(String campusCode){
        switch (campusCode){
            case "uib":
                return "KEY6ytu6esu9";
            case "uio":
                return "";
            default:
                return "";
        }

    }
}