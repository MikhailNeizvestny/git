/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.xml;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;
/**
 *
 * @author Cyfralus
 */
public class XmlTask {
    
    private Document document;
    private String path;
    
    public XmlTask()
            throws ParserConfigurationException, IOException, SAXException
    {
        this("RPIS41/Neizvestny/wdad/learn/xml/newXMLDocument.xml");
    }
    
    public XmlTask(String path) 
            throws ParserConfigurationException, IOException, SAXException
    {
        this.path = path;
        createDocument();
    }
    
    private void createDocument()
            throws ParserConfigurationException, IOException, SAXException
    {
        File xmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.parse(xmlFile);
    }
    
    
    /*public double getBill(String street, int buildingNumber, int flatNumber)
    {
        
    }
    
    public void setTariff(String tariffName, double newValue) 
    {
        
    }
    
    public void addRegistration(String street, int buildingNumber, int flatNumber,
            int year, int month, double coldWater, double hotWater, 
            double electricity, double gas)
    {
        
    }*/

}
