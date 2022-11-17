package com.client.client.controller;

import java.io.File;

public interface IGloabal {
    String SEPARATOR = File.separator;

    String PROJECT_PATH = new File("").getAbsolutePath();

    String FOLDER_DTD = PROJECT_PATH
            + SEPARATOR + "communication"
            + SEPARATOR + "DTD";

    // x001 Message are for PRODUCT REF / DATE
    String CODE_QUESTION_PRODUCT = "q001";
    String CODE_RESPONSE_PRODUCT = "r001";
}
