package com.server.server.controller.question;

import java.io.File;
import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import com.server.server.controller.response.CreateResponse;
import com.server.server.controller.response.IResponse;
import com.server.server.model.Issue;
import com.server.server.model.Product;
import com.server.server.service.issue.IssueService;
import com.server.server.service.product.ProductService;
import com.server.server.utils.BeanUtil;
import com.server.server.utils.XML.XMLReader;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConvertQuestion {
    private XMLReader xmlReader = new XMLReader();

    private ProductService serviceProduct = BeanUtil.getBean(ProductService.class);
    private IssueService serviceIssue = BeanUtil.getBean(IssueService.class);

    /**
     * Convert XML question to SQL statement
     * 
     * @param doc
     * @throws XPathExpressionException
     */
    public void codex001(Document doc) throws XPathExpressionException {

        // parse the xml file
        xmlReader.parseXML(doc.getDocumentElement());

        // extract the data
        String ref = xmlReader.getNodeXPath(doc, "/question/product/ref");
        String date = xmlReader.getNodeXPath(doc, "/question/product/date");

        // Search in the DB
        ArrayList<Product> arrP = serviceProduct.findProductByRefAndDate(ref, date);
        ArrayList<Issue> arrI = new ArrayList<>();

        for (Product p : arrP)
            arrI.add(serviceIssue.findIssueByProductId(p.getId()));

        new CreateResponse().writeResponseCodex001(new File(IResponse.FOLDER_RESPONSE), arrI);
    }

    /**
     * Convert XML question to SQL statement
     * 
     * @param doc
     */
    public void codex002(Document doc) {
        // parse the xml file
        xmlReader.parseXML(doc.getDocumentElement());

        // extract the data
        String ref = xmlReader.getXmlNodeContent().get(0);
        String brand = xmlReader.getXmlNodeContent().get(1);

        // Search in the DB
        ArrayList<Product> arrP = serviceProduct.findProductByRefAndBrand(ref, brand);
        ArrayList<Issue> arrI = new ArrayList<>();

        for (Product p : arrP)
            arrI.add(serviceIssue.findIssueByProductId(p.getId()));

        new CreateResponse().writeResponseCodex001(new File(IResponse.FOLDER_RESPONSE), arrI);
    }
}
