/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import java.util.List;
/**
 *
 * @author Cyfralus
 */
public class Flat {
    int number;
    int personsQuantity;
    double area;
    List<Registration> registration;
    
    public Flat (int number, int personsQuantity, double area, List<Registration> registration)
    {
        this.area = area;
        this.number = number;
        this.personsQuantity = personsQuantity;
        this.registration = registration;
    }
}
