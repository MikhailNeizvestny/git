/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.xml;

public class TestXmlTask {

    public static void main(String[] args) {
        try {
            XmlTask xml = new XmlTask();
            System.out.println(xml.getBill("Gastello", 14, 25));
            xml.addRegistration("Gastello", 14, 25, 2016, 8, 264, 176, 1145, 470);
            xml.setTariff("coldwater", 15);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
