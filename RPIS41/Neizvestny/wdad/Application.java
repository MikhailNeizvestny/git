/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad;
import RPIS41.Neizvestny.wdad.learn.xml.XmlTask;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
/**
 *
 * @author Cyfralus
 */
public class Application {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException
    {
        XmlTask xml = new XmlTask();
        System.out.println(xml.getBill("Gastello", 14, 25));
        System.out.println("I'm Neizvestny Mikhail, and I'm not a monkey!");
    }
}
