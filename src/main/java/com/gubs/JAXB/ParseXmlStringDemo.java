package com.gubs.JAXB;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class ParseXmlStringDemo {

	public static void main(String[] args) {
		try {
			String xml = "<Employee><firstName>Yashwant</firstName><lastName>Chavan</lastName></Employee>";
			JAXBContext jc = JAXBContext.newInstance(Employee.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			StreamSource streamSource = new StreamSource(new StringReader(xml));
			JAXBElement<Employee> je = unmarshaller.unmarshal(streamSource, Employee.class);

			Employee employee = (Employee) je.getValue();
			System.out.println("First Name:- " + employee.getFirstName());
			System.out.println("Last Name:- " + employee.getLastName());

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
