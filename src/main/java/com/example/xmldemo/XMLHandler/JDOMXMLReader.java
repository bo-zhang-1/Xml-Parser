package com.example.xmldemo.XMLHandler;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.example.xmldemo.pojo.Employee;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.StAXEventBuilder;
import org.jdom2.input.StAXStreamBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @Author: bo zhang
 * @Description:
 * @Date:Create：in 2018-09-22 21:39
 * @Modified By：暂无
 */
public class JDOMXMLReader {
    //使用JDOM的好处是可以轻松地从SAX切换到DOM到STAX Parser，您可以提供工厂方法让客户端应用程序选择实现。
    public static void main(String[] args) {
        final String fileName = "D:/spring-boot/xml-demo/src/main/java/com/example/xmldemo/XMLHandler/employee.xml";
        org.jdom2.Document jdomDoc;
        try {
            //we can create JDOM Document from DOM, SAX and STAX Parser Builder classes
            jdomDoc = useDOMParser(fileName);
           // jdomDoc = useSAXParser(fileName);
          //  jdomDoc = useSTAXParser(fileName,"stream");
            Element root = jdomDoc.getRootElement();
            List<Element> empListElements = root.getChildren("Employee");
            List<Employee> empList = new ArrayList<>();
            for (Element empElement : empListElements) {
                Employee emp = new Employee();
               // emp.setId(Integer.parseInt(empElement.getAttributeValue("id")));
                emp.setAge(Integer.parseInt(empElement.getChildText("age")));
                emp.setName(empElement.getChildText("name"));
                emp.setRole(empElement.getChildText("role"));
                emp.setGender(empElement.getChildText("gender"));
                empList.add(emp);
            }
            //lets print Employees list information
            for (Employee emp : empList)
                System.out.println(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //Get JDOM document from DOM Parser
    private static org.jdom2.Document useDOMParser(String fileName)
            throws ParserConfigurationException, SAXException, IOException {
        //creating DOM Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(fileName));
        DOMBuilder domBuilder = new DOMBuilder();
        return domBuilder.build(doc);

    }

    //Get JDOM document from SAX Parser
    private static org.jdom2.Document useSAXParser(String fileName) throws JDOMException,
            IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(new File(fileName));
    }

    //Get JDOM Document from STAX Stream Parser or STAX Event Parser
    private static org.jdom2.Document useSTAXParser(String fileName, String type) throws FileNotFoundException, XMLStreamException, JDOMException{
        if(type.equalsIgnoreCase("stream")){
            StAXStreamBuilder staxBuilder = new StAXStreamBuilder();
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            return staxBuilder.build(xmlStreamReader);
        }
        StAXEventBuilder staxBuilder = new StAXEventBuilder();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
        return staxBuilder.build(xmlEventReader);

    }
}
