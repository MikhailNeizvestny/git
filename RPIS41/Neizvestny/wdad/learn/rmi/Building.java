/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

/**
 *
 * @author Cyfralus
 */
public class Building {
    private String street;
    private String number;
    
    public Building(String street, String number)
    {
        this.number = number;
        this.street = street;
    }
    
    public String getStreet()
    {
        return street;
    }
    
    public String getNumber()
    {
        return number;
    }
}
