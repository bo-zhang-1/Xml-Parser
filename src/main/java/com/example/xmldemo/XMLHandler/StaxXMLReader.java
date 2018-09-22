package com.example.xmldemo.XMLHandler;


import com.example.xmldemo.pojo.Employee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * @Author: bo zhang
 * @Description:
 * @Date:Create：in 2018-09-22 21:13
 * @Modified By：暂无
 */
public class StaxXMLReader {

    public static void main(String[] args) {
        String fileName = "D:/spring-boot/xml-demo/src/main/java/com/example/xmldemo/XMLHandler/employee.xml";
        List<Employee> empList = parseXML(fileName);
        for(Employee emp : empList){
            System.out.println(emp.toString());
        }
    }

    private static List<Employee> parseXML(String fileName) {
        List<Employee> empList = new ArrayList<>();
        Employee emp = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()){
                    StartElement startElement = xmlEvent.asStartElement();
                    if(startElement.getName().getLocalPart().equals("Employee")){
                        emp = new Employee();
                        //Get the 'id' attribute from Employee element
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        /*if(idAttr != null){
                            emp.setId(Integer.parseInt(idAttr.getValue()));
                        }*/
                    }
                    //set the other varibles from xml elements
                    else if(startElement.getName().getLocalPart().equals("age")){
                        xmlEvent = xmlEventReader.nextEvent();
                    // 这里得注意一下，如果age可能为空则需要这样来判断一下
                        if(xmlEvent.isEndElement()) {
                            emp.setAge(Integer.parseInt("1000"));
                        }
                        else
                        {
                            emp.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }

                    }else if(startElement.getName().getLocalPart().equals("name")){
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setName(xmlEvent.asCharacters().getData());
                    }else if(startElement.getName().getLocalPart().equals("gender")){
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setGender(xmlEvent.asCharacters().getData());
                    }else if(startElement.getName().getLocalPart().equals("role")){
                        xmlEvent = xmlEventReader.nextEvent();
                        emp.setRole(xmlEvent.asCharacters().getData());
                    }
                }
                //if Employee end element is reached, add employee object to list
                if(xmlEvent.isEndElement()){
                    EndElement endElement = xmlEvent.asEndElement();
                    System.out.println("取到的结束标签"+endElement.getName().getLocalPart());
                    if(endElement.getName().getLocalPart().equals("Employee")){
                        empList.add(emp);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return empList;
    }
}
