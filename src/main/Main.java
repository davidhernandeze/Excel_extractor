/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.History;
import controller.MainController;
import controller.Query;
import java.util.Date;
import view.MainMenu;

/**
 *
 * @author Tony
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MainMenu menu = new MainMenu();
        MainController controller = new MainController(menu);
        
    }
    
}
