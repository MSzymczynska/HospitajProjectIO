/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author Jakub
 */
public class DateConverter {
    
    
    
     public int convertMonths(String s) {
        int tmp = 1;
        if (s == "Jan") {
            tmp =1;
        }
        if (s == "Feb") {
            tmp = 2;
        }
        if (s == "Mar") {
            tmp = 3;
        }
        if (s == "Apr") {
            tmp = 4;
        }
        if (s == "May") {
            tmp = 5;
        }
        if (s == "Jun") {
            tmp = 6;
        }
        if (s == "Jul") {
            tmp = 7;
        }
        if (s == "Aug") {
            tmp = 8;
        }
        if (s == "Sep") {
            tmp = 9;
        }
        if (s == "Oct") {
            tmp = 10;
        }
        if (s == "Nov") {
            tmp = 11;
        }
        if (s == "Dec") {
            tmp = 12;
        }
        return tmp;
    }
    
        public String[] zwrocModelDni(int n) {
        String[] model = new String[n];

        for (int i = 0; i < n; i++) {
            int j = i + 1;
            if (j < 10) {
                model[i] = "" + 0 + j + "";
            } else {
                model[i] = "" + j + "";
            }
        }
        return model;
    }

    public String[] zwrocModelLat(int n) {
        String[] model = new String[n];

        for (int i = 0; i < n; i++) {
            int j = 2015 + i;
            model[i] = "" + j + "";
        }
        return model;
    }

    public String[] zwrocModelNr() {
        String[] model = new String[36];
        for (int i = 0; i < 36; i++) {
            int j = i + 1;
            model[i] = "" + j + "";
        }
        return model;
    }
    
    
    
    
}
