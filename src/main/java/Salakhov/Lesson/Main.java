package Salakhov.Lesson;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class Main {
    private static class AdvancedXMLHandler extends DefaultHandler {
        private String commonElement,
                botanicalElement,
                zoneElement,
                lightElement,
                priceElement,
                availabitityElement,
                lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            log.info("<" + lastElementName + ">");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = new String(ch, start, length);
            content = content.replace("\n", "").trim();
            log.info(content);
            if (!content.isEmpty()) {
                if (lastElementName.equals("COMMON"))
                    commonElement = content;
                if (lastElementName.equals("BOTANICAL"))
                    botanicalElement = content;
                if (lastElementName.equals("ZONE"))
                    zoneElement = content;
                if (lastElementName.equals("LIGHT"))
                    lightElement = content;
                if (lastElementName.equals("PRICE"))
                    priceElement = content;
                if (lastElementName.equals("AVAILABILITY"))
                    availabitityElement = content;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((commonElement != null && !commonElement.isEmpty()) &&
                    (botanicalElement != null && !botanicalElement.isEmpty()) &&
                    (zoneElement != null && !zoneElement.isEmpty()) &&
                    (lightElement != null && !lightElement.isEmpty()) &&
                    (priceElement != null && !priceElement.isEmpty()) &&
                    (availabitityElement != null && !availabitityElement.isEmpty())) {
                System.out.println("common=" + commonElement + "," +
                        " botanical=" + botanicalElement +
                        ", zone=" + zoneElement +
                        ", light=" + lightElement +
                        ", price=" + priceElement +
                        ", availabitity=" + availabitityElement);
                commonElement = botanicalElement = zoneElement = lightElement = priceElement = availabitityElement = null;
            }
            log.info("</" + qName + ">");
        }
    }

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        System.out.println("Читаем XML файл с помощью SAX парсера...");
        System.out.println("И выводим заодно содержимое в отдельный файл при помощи логгера...");
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
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getTextContent().replace("\n", "").replace("        ", " ").trim());
        }
        Writer writer = new StringWriter();
        XMLSerializer xmlSerializer = new XMLSerializer();
        System.out.println("=========================================");
        System.out.println("Добавим новый элемент...");
        Element catalogElement = document.getDocumentElement();
        Element plantElement = document.createElement("PLANT");
        catalogElement.appendChild(plantElement);
        addStringElement(document, plantElement, "COMMON", "Наше растение");
        addStringElement(document, plantElement, "BOTANICAL", "botanical - Наше растение");
        addStringElement(document, plantElement, "ZONE", "223456");
        addStringElement(document, plantElement, "LIGHT", "По большей части тень - Наше растение");
        addStringElement(document, plantElement, "PRICE", "$12345.12");
        addStringElement(document, plantElement, "AVAILABILITY", "0987654321 - Наше растение");
        System.out.println("=========================================");
        System.out.println("Сохраним новый документ в файл src/main/resources/example_out.xml ...");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/example_out.xml"));
        xmlSerializer.setOutputCharStream(bufferedWriter);
        xmlSerializer.serialize(document);
        bufferedWriter.close();
        System.out.println("=========================================");
        System.out.println("Создаем новый документ для проверки валидации...");
        document = documentBuilder.newDocument();
        Element libraryElement = document.createElement("Library");
        document.appendChild(libraryElement);
        Element bookElement = document.createElement("Book");
        libraryElement.appendChild(bookElement);
        Element authorElement = document.createElement("Author");
        bookElement.appendChild(authorElement);
        addStringElement(document, authorElement, "firstName", "Иванов");
        addStringElement(document, authorElement, "lastName", "Иван");
        addStringElement(document, authorElement, "secondName", "Иванович");
        bookElement.setAttribute("pages", "100");
        bookElement.setAttribute("title", "Название книги");
        bookElement.setAttribute("publisher", "Издательство");
        writer = new StringWriter();
        xmlSerializer.setOutputCharStream(writer);
        xmlSerializer.serialize(document);
        System.out.println(writer.toString());
        System.out.println("=========================================");
        System.out.println("Проверка валидации...");
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File xmlSchemaFile = new File("src/main/resources/book.xsd");
        Schema schema = schemaFactory.newSchema(xmlSchemaFile);
        Validator validator = schema.newValidator();
        DOMSource domSource = new DOMSource(document);
        try {
            validator.validate(domSource);
            System.out.println(" is valid.");
        } catch (SAXException ex) {
            System.out.println(" is not valid because ");
            System.out.println(ex.getMessage());
        }
        System.out.println("=========================================");
        System.out.println("А теперь добавим еще одно отчество и сделаем проверку валидации...");
        addStringElement(document, authorElement, "secondName", "Иванович222");
        writer = new StringWriter();
        xmlSerializer.setOutputCharStream(writer);
        xmlSerializer.serialize(document);
        System.out.println(writer.toString());
        try {
            validator.validate(domSource);
            System.out.println(" is valid.");
        } catch (SAXException ex) {
            System.out.println(" is not valid because ");
            System.out.println(ex.getMessage());
        }
    }

    private static void addStringElement(Document document, Element element, String name, String text) {
        Element newElement = document.createElement(name);
        newElement.setTextContent(text + "\n");
        element.appendChild(newElement);
    }
}
