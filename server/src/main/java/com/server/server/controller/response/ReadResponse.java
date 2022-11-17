package com.server.server.controller.response;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.server.server.utils.FileSearch;
import com.server.server.utils.OSUtil;

@Controller
public class ReadResponse implements IResponse {
    // private XMLReader xmlReader = new XMLReader();

    /**
     * Search the message code
     * 
     * @param doc
     * @return "code"
     */
    private String getMessageCode(Document doc) {
        NodeList nodeList = doc.getElementsByTagName("code");
        return nodeList.item(0).getAttributes().getNamedItem("type").getNodeValue();
    }

    /**
     * Read the xml file and convert it to SQL statement
     * 
     * @param doc
     */
    private void convertResponseCodex001(Document doc) {
        // parse the xml file
        // xmlReader.parseXML(doc.getDocumentElement());
        System.out.println("JE PARSE !");
        // Save on corresponding Object
        // xmlReader.parseXMLFromNode(doc, "client");
        // System.out.println(xmlReader.getXmlNodeContent().toString());
    }

    // private void readFile(File f) {

    // try {
    // Scanner myReader = new Scanner(f);

    // while (myReader.hasNextLine()) {
    // String data = myReader.nextLine();
    // System.out.println(data);
    // }

    // myReader.close();
    // } catch (FileNotFoundException e) {
    // System.out.println("An error occurred.");
    // e.printStackTrace();
    // }
    // }

    /**
     * Process the response, search the code, and content
     * 
     * @param xmlFile
     */
    public void processResponse(File xmlFile) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        System.out.println("*******************");

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            System.out.println("-----------------");
            // Retrieve data depending on the code of the message
            // String code = xmlReader.getMessageCode(doc);
            String code = getMessageCode(doc);

            // process response with code x001
            if (code.equals("x001"))
                convertResponseCodex001(doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readResponse(String filename) {
        File file = null;

        // Choice of the Path OS
        if (OSUtil.isWindows()) {
            String filePath = FOLDER_RESPONSE + SEPARATOR + filename;

            filePath.replace("\\", "\\\\");
            file = new File(filePath);
        } else
            file = new File(FOLDER_RESPONSE + SEPARATOR + filename);
        System.out.println("*/*/*/*/*/*/*/*/*/*/*/*//");

        // Convert the Response into Data Object
        processResponse(file);

        // Get the number of archived file
        FileSearch fs = new FileSearch(new File(FOLDER_ARCHIVED_RESPONSE), 1);
        String name = "response" + fs.getFileInDepth().size() + ".xml";

        // Move the file inside the Archive folder
        file.renameTo(new File(FOLDER_ARCHIVED_RESPONSE + SEPARATOR + name));
    }
}