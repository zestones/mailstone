package com.client.client.utils.XML;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.client.client.controller.IGloabal;
import com.client.client.utils.XML.message.question.Qcodex001;

public class XMLWriter implements IGloabal {

    private static String formatXML(String xml) throws TransformerException {

        // write data to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print by indention
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // add standalone="yes", add line break before the root element
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));

        return output.toString();
    }

    public void writeXML(String path, String code) {
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            chooseTypeMessage(out, code);

            // standard way to convert byte array to String
            String xml = new String(out.toByteArray(), StandardCharsets.UTF_8);
            String prettyPrintXML = formatXML(xml);

            // write to file
            Files.writeString(Paths.get(path), prettyPrintXML, StandardCharsets.UTF_8);

        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void chooseTypeMessage(ByteArrayOutputStream out, String code) {
        switch (code) {
            // message question : lister les produits par ref et date issue
            case CODE_QUESTION_PRODUCT:
                // write XML to ByteArrayOutputStream
                Qcodex001.createXMLContent(out, code);
                break;
        }
    }
}
