/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;
/**
 *
 * @author Cyfralus
 */
public interface XmlDataManager extends Remote, Serializable 
{
    public double getBill(Building building, int flatNumber);
    public double getFlat (Building building, int flatNumber);
    public  void  setTariff  (String tariffName,  double  newValue);
    public void addRegistration (Building building, int flatNumber, Registration registration);
}
