package com.client.client.controller.response;

import java.io.File;
import java.util.ArrayList;

import com.client.client.model.Product;
import com.client.client.utils.FileSearch;
import com.client.client.utils.XML.XMLWriter;
import com.client.client.utils.XML.message.response.Rcodex001;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateResponse implements IResponse {

    public void writeResponseCodex001(File folder, ArrayList<Product> res) {
        // Get the files inside the folder
        FileSearch fs = new FileSearch(folder, 1);
        fs.printFilesInDepth();

        // Get the number of files
        int numberFiles = fs.getFileInDepth().size();
        String filename = "response" + numberFiles + ".xml";

        // Creation of the xml File
        Rcodex001.setData(res, FOLDER_DTD + SEPARATOR + "product.dtd");
        new XMLWriter().writeXML(FOLDER_RESPONSE + SEPARATOR + filename, CODE_RESPONSE_PRODUCT);
    }
}
