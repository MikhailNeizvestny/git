/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import RPIS41.Neizvestny.wdad.utils.PreferencesConstantManager;

import RPIS41.Neizvestny.wdad.data.managers.PreferencesManager;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Date;

/**
 *
 * @author Cyfralus
 */
public class Client 
{
    static private PreferencesManager pm;
    private static final String DATA_MANAGER_NAME = "XmlDataManager";
    
    public static void main(String[] args) 
    {
        try 
        {
            pm = PreferencesManager.getInstance();
        } catch (ParserConfigurationException | IOException | SAXException ex) 
        {
            ex.printStackTrace();
        }
        System.setProperty("java.rmi.server.codebase", pm.getClassProvider());
        System.setProperty("java.rmi.server.useCodebaseOnly", String.valueOf(pm.getProperty(PreferencesConstantManager.USE_CODE_BASE_ONLY)));
        System.setProperty("java.security.policy", pm.getPolicyPath());
        
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(pm.getRegistryAddress(), pm.getRegistryPort());
        } catch (RemoteException re) 
        {
            re.printStackTrace();
        }
        if (registry != null) 
        {
            try 
            {
                XmlDataManager xdm = (XmlDataManager) registry.lookup(DATA_MANAGER_NAME);
                Building building = new Building("Gastello", 14);
                Date date = new Date(2014, 6, 24);
                Registration reg = new Registration(date, 45, 789, 45, 123);
                xdm.addRegistration(building, 25, reg);
                System.out.println(xdm.getBill(building, 25));
                Flat flat = xdm.getFlat(building, 25);
                System.out.println(flat.getNumber());
                System.out.println(flat.getPersonsQuantity());
                xdm.setTariff("coldwater", 14);
            }
            catch (RemoteException re) 
            {
                re.printStackTrace();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
    }
}
