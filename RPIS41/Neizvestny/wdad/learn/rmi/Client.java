/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import RPIS41.Neizvestny.wdad.data.managers.PreferencesManager;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

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
        //System.setProperty("java.rmi.server.useCodebaseOnly", String.valueOf(pm.getProperty(PreferencesConstantManager.USE_CODE_BASE_ONLY)));
        System.setProperty("java.security.policy", pm.getPolicyPath());
        
        //Calendar calendar = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(pm.getRegistryAddress(), pm.getRegistryPort());
        } catch (RemoteException re) 
        {
            re.printStackTrace();
        }
       /* if (registry != null) 
        {
            try 
            {
                XmlDataManager xdm = (XmlDataManager) registry.lookup(DATA_MANAGER_NAME);
            } catch (NotBoundException nbe) 
            {
                System.err.println("Cant find object");
                nbe.printStackTrace();
            } catch (RemoteException re) 
            {
                System.err.println("Cant execute RMI");
                re.printStackTrace();
            } catch (Exception e) 
            {
                System.err.println("User input error");
                e.printStackTrace();
            }
        }*/
    }
}
