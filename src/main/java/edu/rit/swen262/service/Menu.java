package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<String> optionsList;
    //key stroke listener here;

    public String moveMenu() {
        this.optionsList = new ArrayList<String>();
        for (int i = 1; i < 9; i++){
            this.optionsList.add("[" + i + "] Move");
        }
        //key stroke listener 
        //resolve menu(e)
        return "a menu?";
    }
}