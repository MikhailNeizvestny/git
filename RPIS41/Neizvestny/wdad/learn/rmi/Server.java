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
public class Server {

    static private PreferencesManager pm;
    private static final String DATA_MANAGER_NAME = "XmlDataManager";
    private static final String DATA_MANAGER_PATH = "RPIS41.Neizvestny.wdad.learn.rmi.XmlDataManagerImpl";
    private static final int DATA_MANAGER_PORT = 49100;
    public static void main(String[] args) {
        System.setProperty("java.rmi.server.codebase", pm.getClassProvider());
        System.setProperty("java.rmi.server.useCodebaseOnly", "true"); 
        System.setProperty("java.rmi.server.logCalls", "true");
        System.setProperty("java.security.policy", pm.getPolicyPath()); 
        try {
            pm = PreferencesManager.getInstance();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        Registry registry = null;
        try 
        {
            if (pm.getCreateRegistry()) 
            {
                registry = LocateRegistry.createRegistry(pm.getRegistryPort());
            } 
            else 
            {
                registry = LocateRegistry.getRegistry(pm.getRegistryAddress(), pm.getRegistryPort());
            }
        } catch (RemoteException e) 
        {
            e.printStackTrace();
        }

        if (registry != null) 
        {
            try {
                System.out.println("exporting object...");
                XmlDataManagerImpl manager = new XmlDataManagerImpl();
                UnicastRemoteObject.exportObject(manager, DATA_MANAGER_PORT);
                registry.rebind(DATA_MANAGER_NAME, manager);
                //Addbinded
                System.out.println("idl-ing");
            } catch (RemoteException re) {
                System.err.println("cant export or bind object");
                re.printStackTrace();
            }
        }

    }
}
