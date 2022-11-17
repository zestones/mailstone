package com.client.client.controller.question;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.client.client.controller.response.CreateResponse;
import com.client.client.controller.response.IResponse;
import com.client.client.model.Product;
import com.client.client.service.product.ProductService;
import com.client.client.utils.BeanUtil;
import com.client.client.utils.FileSearch;
import com.client.client.utils.OSUtil;
import com.client.client.utils.XML.XMLReader;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReadQuestion implements IQuestion {

    private ProductService service = BeanUtil.getBean(ProductService.class);
    private XMLReader xmlReader = new XMLReader();

    /**
     * Read the xml file and convert it to SQL statement
     * 
     * @param doc
     */
    private void convertQuestionCodex001(Document doc) {
        // parse the xml file
        xmlReader.parseXML(doc.getDocumentElement());

        // extract the data
        String ref = xmlReader.getXmlNodeContent().get(0);
        String date = xmlReader.getXmlNodeContent().get(1);

        System.out.println("ref : " + ref + "\ndate : " + date);

        // Search in the DB
        ArrayList<Product> p = service.findProductByRefAndDate(ref, date);
        new CreateResponse().writeResponseCodex001(new File(IResponse.FOLDER_RESPONSE), p);

        // ! *** for dev ***
        System.out.println("P :" + p.toString());
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
            String code = xmlReader.getMessageCode(doc);

            // process question with code x001
            if (code.equals(CODE_QUESTION_PRODUCT))
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

        // Choice of the Path OS
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
