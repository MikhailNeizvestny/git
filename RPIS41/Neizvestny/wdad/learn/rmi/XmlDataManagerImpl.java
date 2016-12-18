/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import RPIS41.Neizvestny.wdad.learn.xml.XmlTask;
import java.io.IOException;
import java.time.Instant;
import org.w3c.dom.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Cyfralus
 */
public class XmlDataManagerImpl implements XmlDataManager{
    
    private final XmlTask xmltask = new XmlTask();
    
    @Override
    public double getBill(Building building, int flatNumber)
    {
        return xmltask.getBill(building.getStreet(), building.getNumber(), flatNumber);
    }
    
    @Override
    public Flat getFlat(Building building, int flatNumber)
    {
        Element elem =  xmltask.getFlat(building.getStreet(), building.getNumber(), flatNumber);
        double area = Double.valueOf(elem.getAttribute("area"));
        int number = Integer.valueOf(elem.getAttribute("number"));
        int personQuantity = Integer.valueOf(elem.getAttribute("personquantity"));
        List<Registration> registrationList = getRegistrations(elem);
        
        return new Flat(number, personQuantity, area, registrationList);
    }
    
    private List<Registration> getRegistrations(Element elem)
    {
        ArrayList<Registration> regList = new ArrayList<>();
        Registration reg;
        Date date;
        double coldwater;
        double hotwater;
        double electricity;
        double gas;
        NamedNodeMap regAttributes;
        NodeList registrations = elem.getElementsByTagName("registration");
        for(int i = 0; i < registrations.getLength(); i++)
        {
            regAttributes = registrations.item(i).getAttributes();
            date = new Date();
            date.setMonth(Integer.valueOf(regAttributes.getNamedItem("month").getNodeValue()));
            date.setYear(Integer.valueOf(regAttributes.getNamedItem("year").getNodeValue()));
            coldwater = Double.valueOf(((Element)registrations.item(i)).getElementsByTagName("coldwater").item(0).getTextContent());
            hotwater = Double.valueOf(((Element)registrations.item(i)).getElementsByTagName("hotwater").item(0).getTextContent());
            electricity = Double.valueOf(((Element)registrations.item(i)).getElementsByTagName("electricity").item(0).getTextContent());
            gas = Double.valueOf(((Element)registrations.item(i)).getElementsByTagName("gas").item(0).getTextContent());
            reg = new Registration(date, coldwater, hotwater, electricity, gas);
            regList.add(reg);
        }
        return regList;
    }
    
    @Override
    public void setTariff(String tariffName, double newValue)throws IOException
    {
        xmltask.setTariff(tariffName, newValue);
    }
    
    @Override
    public void addRegistration (Building building, int flatNumber, Registration registration) throws IOException
    {
        xmltask.addRegistration(building.getStreet(), building.getNumber(), flatNumber, flatNumber, flatNumber, flatNumber, flatNumber, flatNumber, flatNumber);
    }
}
