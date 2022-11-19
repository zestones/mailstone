package com.client.client.controller.response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.client.client.model.Product;
import com.client.client.model.Products;
import com.client.client.utils.FileSearch;
import com.client.client.utils.OSUtil;
import com.client.client.utils.XML.XMLReader;

@Controller
public class ReadResponse implements IResponse {
    private XMLReader xmlReader = new XMLReader();

    private void convertResponseCodex001(String file)
            throws JAXBException, ParserConfigurationException, SAXException, FileNotFoundException {

        // ! ******* DISABLE DTD ********
        JAXBContext jc = JAXBContext.newInstance(Products.class);
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        spf.setFeature("http://xml.org/sax/features/validation", false);

        org.xml.sax.XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(file));
        SAXSource source = new SAXSource(xmlReader, inputSource);
        // ! ******* DISABLE DTD ********

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Products p = (Products) unmarshaller.unmarshal(source);

        System.out.println(p.toString());
        List<Product> list = p.getListProduct();
        System.out.println(list.toString());
    }

    /**
     * Process the response, search the code, and content
     * 
     * @param xmlFile
     */
    public void processResponse(String xmlFile) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Retrieve data depending on the code of the message
            String code = xmlReader.getMessageCode(doc);

            System.out.println("CODE RESPONSE PRODUCT" + CODE_RESPONSE_PRODUCT);
            System.out.println("File" + xmlFile.toString());

            // process response with code x001
            if (code.equals(CODE_RESPONSE_PRODUCT))
                convertResponseCodex001(xmlFile.toString());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void readResponse(String filename) {
        File file = null;
        String filePath = FOLDER_RESPONSE + SEPARATOR + filename;
        // Choice of the Path OS
        if (OSUtil.isWindows()) {
            filePath.replace("\\", "\\\\");
            file = new File(filePath);
        } else
            file = new File(FOLDER_RESPONSE + SEPARATOR + filename);

        // Convert the Response into Data Object
        processResponse(filePath);

        // Get the number of archived file
        FileSearch fs = new FileSearch(new File(FOLDER_ARCHIVED_RESPONSE), 1);
        String name = "response" + fs.getFileInDepth().size() + ".xml";

        // Move the file inside the Archive folder
        file.renameTo(new File(FOLDER_ARCHIVED_RESPONSE + SEPARATOR + name));
    }
}