package com.client.client.controller.response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.client.client.model.Product;
import com.client.client.utils.FileSearch;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreateResponse implements IResponse {

    String createFile(File folder) {
        if (folder.exists()) {
            // Get the files inside the folder
            FileSearch fs = new FileSearch(folder, 1);
            fs.printFilesInDepth();

            // Get the number of files
            int numberFiles = fs.getFileInDepth().size();
            String filename = "response" + numberFiles + ".xml";

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

    public void writeResponseCodex001(File folder, ArrayList<Product> res) {
        // Create a file for the question
        String filename = createFile(folder);

        // Check if the creation if the file succeeded
        if (filename == null)
            System.err.println("ERROR ! File creation !!");

        // Content of the file
        String xml_start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n"
                + "<!DOCTYPE code SYSTEM \"product.dtd\">" + "\n"
                + "<code type=\"x001\"> " + "\n"
                + "<products>\n\t";

        String xml_body = "";
        for (Product p : res) {
            xml_body += "<product>" + "\n\t\t"
                    + "<ref>" + p.getRef() + "</ref>" + "\n\t\t"
                    + "<date_issue>" + p.getDate() + "</date_issue>" + "\n\t\t"
                    + "<brand>" + p.getBrand() + "</brand>" + "\n\t\t"
                    + "<name>" + p.getName() + "</name>" + "\n\t\t"
                    + "<category>" + p.getCategory().getName() + "</category>" + "\n\t\t"
                    + "<client>\n\t\t\t"
                    + "<firstname>" + p.getClient().getFirstname() + "</firstname>\n\t\t\t"
                    + "<lastname>" + p.getClient().getLastname() + "</lastname>\n\t\t"
                    + "</client>" + "\n\t"
                    + "</product>" + "\n";

        }

        String xml_end = "</products>\n" + "</code>" + "\n";

        try {
            // Write inside the file the xml question
            FileWriter myWriter = new FileWriter(FOLDER_RESPONSE + SEPARATOR + filename, true);
            myWriter.write(xml_start + xml_body + xml_end + "\n");

            myWriter.close();
        } catch (IOException ex) {
            System.out.println("An error occurred.");
            ex.printStackTrace();
        }
    }

}
