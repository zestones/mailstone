package com.client.client.utils.XML.message.response;

import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.client.client.model.Product;

public class Rcodex001 {
    private static String dtdPath;
    private static ArrayList<Product> arr = new ArrayList<Product>();

    public static void setData(ArrayList<Product> p, String dtd) {
        dtdPath = dtd;
        arr = p;
    }

    public static void createXMLContent(OutputStream out, String code) {

        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();

            XMLStreamWriter writer = output.createXMLStreamWriter(out, "UTF-8");

            // Header of the xml file
            writer.writeStartDocument("UTF-8", "1.0");

            writer.writeDTD("<!DOCTYPE code SYSTEM \"" + dtdPath + "\">");

            // <code>
            writer.writeStartElement("code");
            writer.writeAttribute("type", code);

            // <products>
            writer.writeStartElement("products");

            // fill the product list
            fillProductList(writer);

            writer.writeEndElement();
            // </products>

            writer.writeEndDocument();
            // </code>

            writer.flush();

            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void fillProductList(XMLStreamWriter writer) throws XMLStreamException {
        for (Product p : arr) {
            // <product>
            writer.writeStartElement("product");

            // <ref>
            writer.writeStartElement("ref");
            writer.writeCharacters(p.getRef().toString());
            writer.writeEndElement();

            // <date>
            writer.writeStartElement("date");
            writer.writeCharacters(p.getDate().toString());
            writer.writeEndElement();

            // <name>
            writer.writeStartElement("name");
            writer.writeCharacters(p.getName().toString());
            writer.writeEndElement();

            // <brand>
            writer.writeStartElement("brand");
            writer.writeCharacters(p.getBrand().toString());
            writer.writeEndElement();

            // <category>
            writer.writeStartElement("category");
            writer.writeCharacters(p.getCategory().getName().toString());
            writer.writeEndElement();

            // <client>
            writer.writeStartElement("client");

            // <firstname>
            writer.writeStartElement("firstname");
            writer.writeCharacters(p.getClient().getFirstname().toString());
            writer.writeEndElement();

            // <lastname>
            writer.writeStartElement("lastname");
            writer.writeCharacters(p.getClient().getLastname().toString());
            writer.writeEndElement();

            // <email>
            writer.writeStartElement("email");
            writer.writeCharacters(p.getClient().getEmail().toString());
            writer.writeEndElement();

            // <address>
            writer.writeStartElement("address");
            writer.writeCharacters(p.getClient().getAddress().toString());
            writer.writeEndElement();

            // <phoneNumber>
            writer.writeStartElement("phoneNumber");
            writer.writeCharacters(p.getClient().getPhoneNumber().toString());
            writer.writeEndElement();

            writer.writeEndElement();
            // </client>

            writer.writeEndElement();
            // </product>
        }
    }
}