package com.server.server.utils.XML.message.response;

import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.server.server.model.Issue;

public class Rcodex001 {
    private static String dtdPath;
    private static ArrayList<Issue> arr = new ArrayList<Issue>();

    public static void setData(ArrayList<Issue> arrIssue, String dtd) {
        dtdPath = dtd;
        arr = arrIssue;
    }

    public static void createXMLContent(OutputStream out, String code) {

        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();

            XMLStreamWriter writer = output.createXMLStreamWriter(out, "UTF-8");

            // Header of the xml file
            writer.writeStartDocument("UTF-8", "1.0");

            writer.writeDTD("<!DOCTYPE code SYSTEM \"" + dtdPath + "\">");

            // <code>
            writer.writeStartElement("issues");
            writer.writeAttribute("code", code);

            // fill the product list
            fillProductList(writer);

            writer.writeEndDocument();
            // </code>

            writer.flush();

            writer.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void fillProductList(XMLStreamWriter writer) throws XMLStreamException {
        for (Issue i : arr) {
            // <issue>
            writer.writeStartElement("issue");

            // <product>
            writer.writeStartElement("product");

            // <ref>
            writer.writeStartElement("ref");
            writer.writeCharacters(i.getProduct().getRef());
            writer.writeEndElement();

            // <date>
            writer.writeStartElement("date");
            writer.writeCharacters(i.getProduct().getDate().toString());
            writer.writeEndElement();

            // <name>
            writer.writeStartElement("name");
            writer.writeCharacters(i.getProduct().getName().toString());
            writer.writeEndElement();

            // <brand>
            writer.writeStartElement("brand");
            writer.writeCharacters(i.getProduct().getBrand().toString());
            writer.writeEndElement();

            // <category>
            writer.writeStartElement("category");

            // <name>
            writer.writeStartElement("name");
            writer.writeCharacters(i.getProduct().getCategory().getName().toString());
            writer.writeEndElement();

            writer.writeEndElement();
            // </category>

            // <client>
            writer.writeStartElement("client");

            // <firstname>
            writer.writeStartElement("firstname");
            writer.writeCharacters(i.getProduct().getClient().getFirstname().toString());
            writer.writeEndElement();

            // <lastname>
            writer.writeStartElement("lastname");
            writer.writeCharacters(i.getProduct().getClient().getLastname().toString());
            writer.writeEndElement();

            // <email>
            writer.writeStartElement("email");
            writer.writeCharacters(i.getProduct().getClient().getEmail().toString());
            writer.writeEndElement();

            // <address>
            writer.writeStartElement("address");
            writer.writeCharacters(i.getProduct().getClient().getAddress().toString());
            writer.writeEndElement();

            // <phoneNumber>
            writer.writeStartElement("phoneNumber");
            writer.writeCharacters(i.getProduct().getClient().getPhoneNumber().toString());
            writer.writeEndElement();

            writer.writeEndElement();
            // </client>

            writer.writeEndElement();
            // </product>

            // <description>
            writer.writeStartElement("description");
            writer.writeCharacters(i.getDescription().toString());
            writer.writeEndElement();

            // <resolved>
            writer.writeStartElement("resolved");
            writer.writeCharacters(Boolean.toString(i.isResolved()));
            writer.writeEndElement();

            // <solution>
            writer.writeStartElement("solution");

            // <description>
            writer.writeStartElement("description");
            writer.writeCharacters(i.getSolution().getDescription().toString());
            writer.writeEndElement();

            // <cost>
            writer.writeStartElement("cost");
            writer.writeCharacters(Long.toString(i.getSolution().getCost()));
            writer.writeEndElement();

            // <cost>
            writer.writeStartElement("duration");
            writer.writeCharacters(Long.toString(i.getSolution().getDuration()));
            writer.writeEndElement();

            writer.writeEndElement();
            // </solution>

            writer.writeEndElement();
            // </issue>
        }
    }
}
