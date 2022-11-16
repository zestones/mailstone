package com.server.server.utils;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class XMLReader {

    private ArrayList<String> xmlNodeContent = new ArrayList<String>();

    /**
     * Search the message code
     * 
     * @param doc
     * @return "code"
     */
    public String getMessageCode(Document doc) {
        NodeList nodeList = doc.getElementsByTagName("code");
        return nodeList.item(0).getAttributes().getNamedItem("type").getNodeValue();
    }

    /**
     * Parse the xml file Save content in the xmlNodeContent Array
     * 
     * @param startingNode
     */
    public void parseXML(Node startingNode) {
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

    public void parseXMLFromNode(Document doc, String nodeName) {
        Node startingNode = doc.getElementsByTagName(nodeName).item(0);
        parseXML(startingNode);
    }

    /**
     * Get the xmlNodeContent Array
     * 
     * @return"wmlNodeNodeContent"
     */

    public ArrayList<String> getXmlNodeContent() {
        return this.xmlNodeContent;
    }

    /**
     * Set the xmlNodeContent Array
     * 
     * @param xmlNodeContent
     */
    public void setXmlNodeContent(ArrayList<String> xmlNodeContent) {
        this.xmlNodeContent = xmlNodeContent;
    }

}