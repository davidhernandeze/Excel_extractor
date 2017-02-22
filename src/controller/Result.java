/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author Tony
 */
public class Result {
    
    private String name;
    private String tradeName;
    private String person;
    private String number;
    private String notes;

     public Result(String name, String tradeName, String person, String number, String notes){
        
         this.name = name;
         this.tradeName = tradeName;
         this.person = person;
         this.number = number;
         this.notes = notes;
        
    }
     
     
    public String getName() {
        return name;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getPerson() {
        return person;
    }

    public String getNumber() {
        return number;
    }

    public String getNotes() {
        return notes;
    }
    
    public static ArrayList<ArrayList> splitResults(ArrayList completeResult){
        
        
        return null;
    }
}
