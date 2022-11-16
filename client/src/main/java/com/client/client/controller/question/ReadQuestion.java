package com.client.client.controller.question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.client.client.controller.response.CreateResponse;
import com.client.client.controller.response.IResponse;
import com.client.client.model.Product;
import com.client.client.service.product.ProductService;
import com.client.client.utils.BeanUtil;
import com.client.client.utils.FileSearch;
import com.client.client.utils.OSUtil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReadQuestion implements IQuestion {

    private ProductService service = BeanUtil.getBean(ProductService.class);

    ArrayList<String> xmlNodeContent = new ArrayList<String>();

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
     * Parse the xml file
     * Save content in the xmlNodeContent Array
     * 
     * @param startingNode
     */
    private void parseXML(Node startingNode) {
        NodeList childNodes = startingNode.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);

            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                parseXML(childNode);
            } else {

                // <trim> is used to ignore new lines and spaces elements.
                if (!childNode.getTextContent().trim().isEmpty()) {
                    xmlNodeContent.add(childNode.getTextContent());
                }
            }
        }
    }

    /**
     * Read the xml file and convert it to SQL statement
     * 
     * @param doc
     */
    private void convertQuestionCodex001(Document doc) {
        // parse the xml file
        parseXML(doc.getDocumentElement());

        // extract the data
        String ref = xmlNodeContent.get(0);
        String date = xmlNodeContent.get(1);

        System.out.println(date);
        System.out.println(ref);

        // Search on the DB
        ArrayList<Product> p = service.findProductByRefAndDate(ref, date);
        new CreateResponse().writeResponseCodex001(new File(IResponse.FOLDER_RESPONSE), p);

        // ! *** for dev ***
        System.out.println("p :" + p.toString());
        // ! *******
    }

    /**
     * Process the question, search the code, and content
     * 
     * @param xmlFile
     */
    public void processQuestion(File xmlFile) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Covert the message to SQL depending the code of the message
            String code = getMessageCode(doc);

            // process question with code x001
            if (code.equals("x001"))
                convertQuestionCodex001(doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read a question from the QESTION folder
     * 
     * @param filename
     */
    public void readQuestion(String filename) {
        File file = null;

        if (OSUtil.isWindows()) {
            String filePath = FOLDER_QUESTION + SEPARATOR + filename;

            filePath.replace("\\", "\\\\");
            file = new File(filePath);
        } else
            file = new File(FOLDER_QUESTION + SEPARATOR + filename);

        // Convert the Question into SQL
        processQuestion(file);

        // Get the number of archived file
        FileSearch fs = new FileSearch(new File(FOLDER_ARCHIVED_QUESTION), 1);
        String name = "question" + fs.getFileInDepth().size() + ".xml";

        // Move the file inside the Archive folder
        file.renameTo(new File(FOLDER_ARCHIVED_QUESTION + SEPARATOR + name));
    }
}
