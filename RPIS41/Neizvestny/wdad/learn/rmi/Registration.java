/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author Cyfralus
 */
public class Registration implements Serializable{
    Date date;
    private double coldwater;
    private double hotwater;
    private double electricity;
    private double gas;
    
    public Registration(Date date, double coldwater, double hotwater, double electricity, double gas)
    {
        this.date = date;
        this.coldwater = coldwater;
        this.hotwater = hotwater;
        this.electricity = electricity;
        this.gas = gas;
    }
    
    public double getColdwater()
    {
        return coldwater;
    }
    
    public double getHotwater()
    {
        return hotwater;
    }
    
    public double getElectricity()
    {
        return electricity;
    }
    
    public double getGas()
    {
        return gas;
    }
    
    public int getMonth()
    {
        return date.getMonth();
    }
    
    public int getYear()
    {
        return date.getYear();
    }
    
}
