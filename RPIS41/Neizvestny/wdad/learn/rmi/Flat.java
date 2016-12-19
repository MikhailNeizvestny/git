/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import java.util.List;
import java.io.Serializable;
/**
 *
 * @author Cyfralus
 */
public class Flat implements Serializable {
    private int number;
    private int personsQuantity;
    private double area;
    private List<Registration> registration;
    
    public Flat (int number, int personsQuantity, double area, List<Registration> registration)
    {
        this.area = area;
        this.number = number;
        this.personsQuantity = personsQuantity;
        this.registration = registration;
    }
    
    public int getNumber()
    {
        return number;
    }
    
    public int getPersonsQuantity()
    {
        return personsQuantity;
    }
    
    public double getArea()
    {
        return area;
    }
    
    public List<Registration> getRegistration()
    {
        return registration;
    }
}
