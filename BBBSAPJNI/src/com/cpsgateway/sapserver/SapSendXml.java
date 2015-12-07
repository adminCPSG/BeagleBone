package com.cpsgateway.sapserver;


import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

//import com.sun.xml.internal.messaging.saaj.util.*;


import javax.xml.messaging.URLEndpoint;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.sap.mw.jco.util.Codecs.Base64;


public class SapSendXml {

	//public static void main(String[] args) {
	public  static void SendXml(String url, String userName, String password, String documentNumber, String data ) {
		try {
			SOAPMessage soapMsg = MessageFactory.newInstance(
					SOAPConstants.SOAP_1_2_PROTOCOL).createMessage();
			final SOAPPart part = soapMsg.getSOAPPart();
			final SOAPEnvelope envelope = part.getEnvelope();
			envelope.setPrefix("soap");
			envelope.removeNamespaceDeclaration("env");

			envelope.setAttribute("xmlns:urn",
					"urn:sap-com:document:sap:soap:functions:mc-style");
			SOAPHeader header = envelope.getHeader();
			header.setPrefix("soap");
			header.removeNamespaceDeclaration("env");
			SOAPBody body = envelope.getBody();
			body.setPrefix("soap");
			body.removeNamespaceDeclaration("env");

			Name name1 = envelope.createName("urn:ZmeasuremDocumRfcSingle001");
			SOAPElement element1 = body.addChildElement(name1);

			org.w3c.dom.Comment cmt1 = part.createComment("--Optional:--");
			element1.appendChild(cmt1);

			Name name2 = envelope.createName("MeasurementPoint");
			SOAPElement element2 = element1.addChildElement(name2);
			//element2.addTextNode("11901");
			element2.addTextNode(documentNumber);

			org.w3c.dom.Comment cmt2 = part.createComment("--Optional:--");
			element1.appendChild(cmt2);

			Name name3 = envelope.createName("RecordedValue");
			SOAPElement element3 = element1.addChildElement(name3);
			//element3.addTextNode(Integer.toString(temp));
			element3.addTextNode(data);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			soapMsg.writeTo(out);
			String str1 = new String(out.toByteArray());

			String str2 = prettyFormat(str1, 2);
			System.out.println(str2);
			System.out.println("SOAP msg created");

			SOAPConnectionFactory connectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = connectionFactory
					.createConnection();
			System.out.println("Connection created");

			//String loginPassword = "developer1:ides123";
			String loginPassword = userName + ":" + password;
			soapMsg.getMimeHeaders().addHeader(
					"Authorization","Basic "
							+ new String(Base64.encode(loginPassword.getBytes())));
			//String url = "http://61.12.64.91:8002/sap/bc/srt/rfc/sap/zmeasurem_docum_rfc_single_002/800/zmeasurem_docum_rfc_single_002/zmeasurem_docum_rfc_single_002?sap-client=800";
			URLEndpoint endpoint = new URLEndpoint(url);
			System.out.println("URL Endpoint created");

			SOAPMessage resp = soapConnection.call(soapMsg, endpoint);
			System.out.println("Response Received:");
			resp.writeTo(System.out);
			System.out.println("\nEnd of Response");
			SOAPBody soapBody = resp.getSOAPBody();
			
			Iterator<?> iterator = soapBody.getChildElements();
			while (iterator.hasNext()) {
				SOAPBodyElement bodyElement = (SOAPBodyElement) iterator.next();

				if (bodyElement.getTagName().contains("ZmeasuremDocumRfcSingle001Response")) {
					Iterator<?> iterator1 = bodyElement.getChildElements();
					while (iterator1.hasNext()) {
						SOAPElement element = (SOAPElement)iterator1.next();
						if (element.getTagName().equals("MeasurementDocument")) {
							System.out.println("The child element is:"
									+ element.getTagName() + " Value = "
									+ element.getValue());
						} else {
							System.out.println("The child element of response is not correct");
						}
						String Mdocum = element.getValue();
						Mdocum = Mdocum.replaceFirst("^0+(?!$)", "");
						System.out.println(Mdocum);
						if (Mdocum.equals("0")) {
							System.out
									.println("You enterd Wrong measurement point");
						}

					}
				}
			}
			soapConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String prettyFormat(String input, int indent) {
		try {
			Source xmlInput = new StreamSource(new StringReader(input));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			// This statement works with JDK 6
			transformerFactory.setAttribute("indent-number", indent);

			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.transform(xmlInput, xmlOutput);
			return xmlOutput.getWriter().toString();
		} catch (Throwable e) {
			try {
				Source xmlInput = new StreamSource(new StringReader(input));
				StringWriter stringWriter = new StringWriter();
				StreamResult xmlOutput = new StreamResult(stringWriter);
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
				transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(
						"{http://xml.apache.org/xslt}indent-amount",
						String.valueOf(indent));

				transformer.transform(xmlInput, xmlOutput);
				return xmlOutput.getWriter().toString();
			} catch (Throwable t) {
				return input;
			}
		}
	}
}
