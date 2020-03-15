package Salakhov.Lesson;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {

    private static class AdvancedXMLHandler extends DefaultHandler {
        private String common, botanical, zone, light, price, availabitity, lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String info = new String(ch, start, length);

            info = info.replace("\n", "").trim();

            if (!info.isEmpty()) {
                if (lastElementName.equals("COMMON"))
                    common = info;
                if (lastElementName.equals("BOTANICAL"))
                    botanical = info;
                if (lastElementName.equals("ZONE"))
                    zone = info;
                if (lastElementName.equals("LIGHT"))
                    light = info;
                if (lastElementName.equals("PRICE"))
                    price = info;
                if (lastElementName.equals("AVAILABILITY"))
                    availabitity = info;

            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( (common != null && !common.isEmpty()) && (botanical != null && !botanical.isEmpty()) &&
                    (zone != null && !zone.isEmpty()) && (light != null && !light.isEmpty()) &&
                    (price != null && !price.isEmpty()) && (availabitity != null && !availabitity.isEmpty()) ) {
                System.out.println("common=" + common + ", botanical=" + botanical +
                        ", zone=" + zone + ", light=" + light +
                        ", price=" + price + ", availabitity=" + availabitity);
                common = botanical = zone = light = price = availabitity = null;
            }
        }
    }



    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {

        System.out.println("Читаем XML файл с помощью SAX парсера...");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        AdvancedXMLHandler advancedXMLHandler = new AdvancedXMLHandler();
        saxParser.parse(new File("src/main/resources/example.xml"), advancedXMLHandler);
        System.out.println("=========================================");
        System.out.println("Читаем XML файл с помощью DOM парсера...");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("src/main/resources/example.xml"));
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("PLANT");
        for (int i=0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getTextContent().replace("\n", "").replace("        ", " ").trim());
        }
        System.out.println("=========================================");
        System.out.println("Выводим содержимое в отдельный файл при помощи логгера...");


    }
}
