package com.cpsgateway.ladderprog;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cpsgateway.localio.LocalIOInit;

public class XmlPortStatus {
	
	public static void createXmlPortStatus() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("item");
			doc.appendChild(rootElement);
		
			// staff elements
			Element entry = doc.createElement("entry");
			rootElement.appendChild(entry);
		
			// firstname elements
			Element portName = doc.createElement("Port");
			portName.appendChild(doc.createTextNode("LDI_00"));
			entry.appendChild(portName);
		
			// lastname elements
			Element comment = doc.createElement("Comment");
			comment.appendChild(doc.createTextNode("switch1"));
			entry.appendChild(comment);
		
			// nickname elements
			Element currentStatus = doc.createElement("CurrentStatus");
			if(LocalIOInit.localDIPorts.get(0).currentValue == true){
				currentStatus.appendChild(doc.createTextNode("1"));
			} else {
				currentStatus.appendChild(doc.createTextNode("0"));
			}
			entry.appendChild(currentStatus);
		
			// staff elements
			Element entry2 = doc.createElement("entry");
			rootElement.appendChild(entry2);
		
			// firstname elements
			Element portName2 = doc.createElement("Port");
			portName2.appendChild(doc.createTextNode("LDI_01"));
			entry2.appendChild(portName2);
		
			// lastname elements
			Element comment2 = doc.createElement("Comment");
			comment2.appendChild(doc.createTextNode("switch2"));
			entry2.appendChild(comment2);
		
			// nickname elements
			Element currentStatus2 = doc.createElement("CurrentStatus");
			if(LocalIOInit.localDIPorts.get(1).currentValue == true){
				currentStatus2.appendChild(doc.createTextNode("1"));
			} else {
				currentStatus2.appendChild(doc.createTextNode("0"));
			}
			entry2.appendChild(currentStatus2);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//StreamResult result = new StreamResult(new File("D:/IoT/Demos/Anant/status.xml"));
			StreamResult result = null;
			if(!LocalIOInit.simulationMode) {
				result = new StreamResult(new File("/var/lib/cloud9/bone101/ladderapp/status.xml"));
			} else {
				result = new StreamResult(new File("../ladderfile/status.xml"));
			}
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
		
			transformer.transform(source, result);
		
			System.out.println("File saved!");
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
