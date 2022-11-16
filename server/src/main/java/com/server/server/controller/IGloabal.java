package com.server.server.controller;

import java.io.File;

public interface IGloabal {
    String SEPARATOR = File.separator;

    String PROJECT_PATH = new File("").getAbsolutePath();

    String FOLDER_DTD = PROJECT_PATH
            + SEPARATOR + "communication"
            + SEPARATOR + "DTD";
}
