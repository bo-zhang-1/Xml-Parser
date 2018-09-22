package com.example.xmldemo.XMLHandler;

import com.example.xmldemo.pojo.Employee;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @Author: bo zhang
 * @Description:
 * @Date:Create：in 2018-09-22 22:27
 * @Modified By：暂无
 */
public class StaxXMLReader2
{
    private static boolean bName;
    private static boolean bAge;
    private static boolean bGender;
    private static boolean bRole;

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
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(fileName));
            int event = xmlStreamReader.getEventType();
            while(true){
                switch(event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if(xmlStreamReader.getLocalName().equals("Employee")){
                            emp = new Employee();
                           // emp.setId(Integer.parseInt(xmlStreamReader.getAttributeValue(0)));
                        }else if(xmlStreamReader.getLocalName().equals("name")){
                            bName=true;
                        }else if(xmlStreamReader.getLocalName().equals("age")){
                            bAge=true;
                        }else if(xmlStreamReader.getLocalName().equals("role")){
                            bRole=true;
                        }else if(xmlStreamReader.getLocalName().equals("gender")){
                            bGender=true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if(bName){
                            emp.setName(xmlStreamReader.getText());
                            bName=false;
                        }else if(bAge){
                            emp.setAge(Integer.parseInt(xmlStreamReader.getText()));
                            bAge=false;
                        }else if(bGender){
                            emp.setGender(xmlStreamReader.getText());
                            bGender=false;
                        }else if(bRole){
                            emp.setRole(xmlStreamReader.getText());
                            bRole=false;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if(xmlStreamReader.getLocalName().equals("Employee")){
                            empList.add(emp);
                        }
                        break;
                }
                if (!xmlStreamReader.hasNext())
                    break;

                event = xmlStreamReader.next();
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return empList;
    }
}
