package com.automatedtesting.support;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

class FileSupport {
    private static final Logger LOG = Logger.getLogger(FileSupport.class);

    String getFileContents(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader().getResource(filePath).toURI()));
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException | URISyntaxException ex) {
            LOG.info(ex.getMessage());
        }

        return contentBuilder.toString().trim();
    }
}
