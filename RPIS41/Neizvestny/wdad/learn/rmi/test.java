/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyfralus
 */
public class test {
    public static void main(String[] args) {
        XmlDataManagerImpl manager = new XmlDataManagerImpl();
        Building building = new Building("Gastello", 14);
        Date date = new Date(2014, 6, 24);
        Registration reg = new Registration(date, 45, 789, 45, 123);
        try {
            manager.addRegistration(building, 25, reg);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        manager.getBill(building, 25);
        Flat flat = manager.getFlat(building, 25);
        try {
            manager.setTariff("coldwater", 14);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("");
        
    }
}
