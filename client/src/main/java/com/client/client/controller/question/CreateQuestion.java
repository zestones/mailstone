package com.client.client.controller.question;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.client.client.utils.FileSearch;

@Controller
public class CreateQuestion implements IQuestion {

    @RequestMapping(value = { "/", "/index" })
    private String redirectUser() {
        return "index";
    }

    @RequestMapping(value = "/hello")
    private String hello() {
        System.out.println("--------- REDIRECTION OK -------------");
        return "index";
    }

    String createFile(File folder) {
        if (folder.exists()) {
            // Get the files inside the folder
            FileSearch fs = new FileSearch(folder, 1);
            fs.printFilesInDepth();

            // Get the number of files
            int numberFiles = fs.getFileInDepth().size();
            String filename = "question" + numberFiles + ".xml";

            try {
                // Create the file
                File file = new File(folder.toString() + SEPARATOR + filename);

                // Check if the file already exist
                if (!file.createNewFile())
                    System.out.println("File already exists.");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return filename;
        } else
            return null;
    }

    /**
     * Write the question in xml inside a file
     * 
     * @param folder
     * @param ref
     * @param date
     */
    void writeQuestionCodex001(File folder, String ref, String date) {
        // Create a file for the question
        String filename = createFile(folder);

        // Check if the creation if the file succeeded
        if (filename == null)
            System.err.println("ERROR ! File creation !!");

        // Content of the file
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n"
                + "<!DOCTYPE code SYSTEM \"" + FOLDER_DTD + SEPARATOR + "product.dtd\">" + "\n"
                + "<code type=\"x001\"> " + "\n"
                + "<product>" + "\n\t"
                + "<ref>" + ref + "</ref>" + "\n\t"
                + "<date_issue>" + date + "</date_issue>" + "\n"
                + "</product>" + "\n"
                + "</code>" + "\n";

        try {
            // Write inside the file the xml question
            FileWriter myWriter = new FileWriter(FOLDER_QUESTION + SEPARATOR + filename, true);
            myWriter.write(xml + "\n");

            myWriter.close();
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

    @PostMapping(value = "/products-ref-date")
    private String product(String ref, String date) {

        if (new File(FOLDER_QUESTION).exists()) {
            writeQuestionCodex001(new File(FOLDER_QUESTION), ref, date);
        }

        return "redirect:/"; // TODO : Display search result
    }
}
