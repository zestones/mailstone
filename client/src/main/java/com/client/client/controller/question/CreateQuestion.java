package com.client.client.controller.question;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.client.client.utils.FileSearch;
import com.client.client.utils.XML.XMLWriter;
import com.client.client.utils.XML.message.question.Qcodex001;

@Controller
public class CreateQuestion implements IQuestion {

    @PostMapping(value = "/products/qst/ref-date")
    private String getProducts(String ref, String date) {

        if (new File(FOLDER_QUESTION).exists()) {
            writeQuestionCodex001(new File(FOLDER_QUESTION), ref, date);
        }

        return "redirect:/"; // TODO : Display search result
    }

    /**
     * Write the question in xml inside a file
     * 
     * @param folder
     * @param ref
     * @param date
     */
    void writeQuestionCodex001(File folder, String ref, String date) {

        // Get the files inside the folder
        FileSearch fs = new FileSearch(folder, 1);
        fs.printFilesInDepth();

        // Get the number of files
        int numberFiles = fs.getFileInDepth().size();
        String filename = "question" + numberFiles + ".xml";

        // Creation of the xml File
        Qcodex001.setData(date, ref, FOLDER_DTD + SEPARATOR + "product.dtd");
        new XMLWriter().writeXML(FOLDER_QUESTION + SEPARATOR + filename, CODE_QUESTION_PRODUCT);
    }
}
