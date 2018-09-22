package com.example.xmldemo.XMLHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.example.xmldemo.pojo.Employee;
import org.xml.sax.SAXException;


/**
 * @Author: bo zhang
 * @Description:  SAX解析测试类
 * @Date:Create：in 2018-09-22 20:55
 * @Modified By：暂无
 */
public class XMLParserSAX {

    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            EmployeeXMLHandler handler = new EmployeeXMLHandler();
            saxParser.parse(new File("D:/spring-boot/xml-demo/src/main/java/com/example/xmldemo/XMLHandler/employee.xml"), handler);
            //Get Employees list
            List<Employee> empList = handler.getEmpList();
            //print employee information
            for(Employee emp : empList)
                System.out.println(emp);
        } catch (ParserConfigurationException  | IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
    }
}