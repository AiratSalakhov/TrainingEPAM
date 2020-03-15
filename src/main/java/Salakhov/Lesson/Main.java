package Salakhov.Lesson;

import ch.qos.logback.core.joran.spi.XMLUtil;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
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

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

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
        System.out.println("Выводим содержимое в отдельный файл при помощи логгера... НЕ РЕАЛИЗОВАНО!!!!!");
        // как это сделать - пока не разобрался ..... :(
        //log.info("test");

        System.out.println("=========================================");
        System.out.println("Добавим новый элемент...");

        Element catalog = document.getDocumentElement();
        Element plant = document.createElement("PLANT");
        catalog.appendChild(plant);
        addStringElement(document, plant, "COMMON", "Наше растение");
        addStringElement(document, plant, "BOTANICAL", "botanical - Наше растение");
        addStringElement(document, plant, "ZONE", "223456");
        addStringElement(document, plant, "LIGHT", "По большей части тень - Наше растение");
        addStringElement(document, plant, "PRICE", "$12345.12");
        addStringElement(document, plant, "AVAILABILITY", "0987654321 - Наше растение");

        System.out.println("=========================================");
        System.out.println("Сохраним новый документ в файл src/main/resources/example_out.xml ...");

        //Writer out = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/example_out.xml"));
        XMLSerializer serializer = new XMLSerializer();
        //serializer.setOutputCharStream(out);
        serializer.setOutputCharStream(bufferedWriter);
        serializer.serialize(document);
        //System.out.println(out.toString());
        bufferedWriter.close();

        //Далее при помощи DOM создать xml документ который будет удовлетворять условия нашей схемы и нужно будет
        // програмно его провалидировать. Для интереса можете сделать дополнительно не валидный документ и провалидировать его.
        System.out.println("=========================================");
        System.out.println("Создаем новый документ для проверки валидации...");
        document = documentBuilder.newDocument();
        Element library = document.createElement("Library");
        document.appendChild(library);
        Element book = document.createElement("Book");
        library.appendChild(book);
        Element author = document.createElement("Author");
        book.appendChild(author);
        addStringElement(document, author, "firstName", "Иванов");
        addStringElement(document, author, "lastName", "Иван");
        addStringElement(document, author, "secondName", "Иванович");
        book.setAttribute("pages", "100");
        book.setAttribute("title", "Название книги");
        book.setAttribute("publisher", "Издательство");

        // эти атрибуты не заполняем, т.к. далее в валидаторе указываем файл схемы напрямую...
        //library.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        //library.setAttribute("xsi:noNamespaceSchemaLocation", "src/main/resources/book.xsd");

        Writer out = new StringWriter();
        serializer.setOutputCharStream(out);
        serializer.serialize(document);
        System.out.println(out.toString());

        System.out.println("=========================================");
        System.out.println("Проверка валидации...");

        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaLocation = new File("src/main/resources/book.xsd");
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        DOMSource source = new DOMSource(document);
        // 5. Валидация документа
        try {
            validator.validate(source);
            System.out.println(" is valid.");
        }
        catch (SAXException ex) {
            System.out.println(" is not valid because ");
            System.out.println(ex.getMessage());
        }
        System.out.println("=========================================");
        System.out.println("А теперь добавим еще одно отчество и сделаем проверку валидации...");
        addStringElement(document, author, "secondName", "Иванович222");
        serializer.serialize(document);
        System.out.println(out.toString());
        try {
            validator.validate(source);
            System.out.println(" is valid.");
        }
        catch (SAXException ex) {
            System.out.println(" is not valid because ");
            System.out.println(ex.getMessage());
        }

    }

    private static void addStringElement(Document document, Element where, String name, String text) {
        Element element = document.createElement(name);
        element.setTextContent(text + "\n");
        where.appendChild(element);
    }
}
