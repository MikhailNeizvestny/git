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

public class XmlTask {

    private Document document;
    private String path;

    public XmlTask(String path)
            throws ParserConfigurationException, IOException, SAXException {
        this.path = path;
        createDocument();
    }

    public XmlTask()
            throws ParserConfigurationException, IOException, SAXException {
        this("src/RPIS41/Neizvestny/wdad/learn/xml/newXMLDocument.xml");
    }

    private void createDocument()
            throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        document = dBuilder.parse(xmlFile);
    }

    private void updateDocument() throws IOException {
        DOMImplementationLS domImplementationLS
                = (DOMImplementationLS) document.getImplementation().getFeature("LS", "3.0");
        LSOutput lsOutput = domImplementationLS.createLSOutput();
        FileOutputStream outputStream = new FileOutputStream(path);
        lsOutput.setByteStream(outputStream);
        LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
        lsSerializer.write(document, lsOutput);
        outputStream.close();
    }

    private class Registration {

        public double coldwaterReg;
        public double hotwaterReg;
        public double electricityReg;
        public double gasReg;
    }

    private Registration getRegistrations(Node registrations) {
        Registration reg = new Registration();
        NodeList coldwater = ((Element) registrations).getElementsByTagName("coldwater");
        NodeList hotwater = ((Element) registrations).getElementsByTagName("hotwater");
        NodeList electricity = ((Element) registrations).getElementsByTagName("electricity");
        NodeList gas = ((Element) registrations).getElementsByTagName("gas");

        reg.coldwaterReg = Double.valueOf(coldwater.item(0).getTextContent());
        reg.hotwaterReg = Double.valueOf(hotwater.item(0).getTextContent());
        reg.electricityReg = Double.valueOf(electricity.item(0).getTextContent());
        reg.gasReg = Double.valueOf(gas.item(0).getTextContent());

        return reg;
    }

    private Element getBuilding(String street, int buildingNumber) {
        NodeList buildingList = document.getElementsByTagName("building");
        Element building = null;
        for (int i = 0; i < buildingList.getLength(); i++) {
            NamedNodeMap buildingAttributes = buildingList.item(i).getAttributes();
            if (buildingAttributes.getNamedItem("street").getNodeValue().equals(street)
                    && Integer.valueOf(buildingAttributes.getNamedItem("number").getNodeValue()) == buildingNumber) {
                building = (Element) buildingList.item(i);
            }
        }
        return building;
    }

    private Element getFlat(String street, int buildingNumber, int flatNumber) {
        Element flat = null;
        NodeList flatList = getBuilding(street, buildingNumber).getElementsByTagName("flat");
        for (int j = 0; j < flatList.getLength(); j++) {
            NamedNodeMap flatAttributes = flatList.item(j).getAttributes();
            if (Integer.valueOf(flatAttributes.getNamedItem("number").getNodeValue()) == flatNumber)// находим квартиру
            {
                flat = (Element) flatList.item(j);
            }
        }
        return flat;
    }

    public double getBill(String street, int buildingNumber, int flatNumber) {
        double result = 0;
        Element flat = getFlat(street, buildingNumber, flatNumber);
        NodeList tariffs = document.getElementsByTagName("tariffs");
        NodeList registrationList;
        NamedNodeMap registrationAttributes;
        Node lastReg, prevReg = null;
        
        registrationList = flat.getElementsByTagName("registration");//получаем список показаний
        lastReg = registrationList.item(registrationList.getLength() - 1);//получим последнее показание
        //предыдущее показание
        for (int k = 0; k < registrationList.getLength(); k++) {
            registrationAttributes = registrationList.item(k).getAttributes();
            if (Integer.valueOf(registrationAttributes.getNamedItem("month").getNodeValue())
                    == Integer.valueOf(lastReg.getAttributes().getNamedItem("month").getNodeValue()) - 1) {
                prevReg = registrationList.item(k);
            }
        }
        //
        Registration regDifference = new Registration();
        Registration prevRegistration = getRegistrations(prevReg);
        Registration lastRegistration = getRegistrations(lastReg);

        regDifference.coldwaterReg = lastRegistration.coldwaterReg - prevRegistration.coldwaterReg;
        regDifference.hotwaterReg = lastRegistration.hotwaterReg - prevRegistration.hotwaterReg;
        regDifference.electricityReg = lastRegistration.electricityReg - prevRegistration.electricityReg;
        regDifference.gasReg = lastRegistration.coldwaterReg - prevRegistration.coldwaterReg;

        NamedNodeMap tariffsAttributes = tariffs.item(0).getAttributes();
        result = regDifference.coldwaterReg * Double.parseDouble(tariffsAttributes.getNamedItem("coldwater").getNodeValue())
                + regDifference.hotwaterReg * Double.parseDouble(tariffsAttributes.getNamedItem("hotwater").getNodeValue())
                + regDifference.electricityReg * Double.parseDouble(tariffsAttributes.getNamedItem("electricity").getNodeValue())
                + regDifference.gasReg * Double.parseDouble(tariffsAttributes.getNamedItem("gas").getNodeValue());
        return result;
    }

    public void setTariff(String tariffName, double newValue) throws IOException {
        NodeList tariffsList = document.getElementsByTagName("tariffs");
        NamedNodeMap tariffAttr = tariffsList.item(0).getAttributes();
        tariffAttr.getNamedItem(tariffName).setNodeValue(String.valueOf(newValue));
        updateDocument();
    }

    //int year, int month, double coldWater, double hotWater,
    //        double electricity, double gas
    public void addRegistration(String street, int buildingNumber, int flatNumber) throws IOException {
        NodeList buildingList = document.getElementsByTagName("building");
        NamedNodeMap buildingAttributes;
        for (int i = 0; i < buildingList.getLength(); i++) {
            buildingAttributes = buildingList.item(i).getAttributes();
            if (buildingAttributes.getNamedItem("street").getNodeValue().equals(street)
                    && Integer.valueOf(buildingAttributes.getNamedItem("number").getNodeValue()) == buildingNumber) {
                NodeList flatList = ((Element) buildingList.item(i)).getElementsByTagName("flat");
                //flatList = buildingList.item(i).getChildNodes(); //получаем список квартир
                for (int j = 0; j < flatList.getLength(); j++) {
                    NamedNodeMap flatAttributes = flatList.item(j).getAttributes();
                    if (Integer.valueOf(flatAttributes.getNamedItem("number").getNodeValue()) == flatNumber)// находим квартиру
                    {
                        Element elem = document.createElement("registration");
                        flatList.item(i).appendChild(elem);
                    }
                }
            }
        }
        updateDocument();
    }

}
