package com.automatedtesting.support;

import org.apache.log4j.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.stream.*;

public class FileSupport {
    private static final Logger LOG = Logger.getLogger(FileSupport.class);

    public String getFileContents(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader().getResource(filePath).toURI()));
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException | URISyntaxException ex) {
            LOG.info(ex.getMessage());
        }
        return contentBuilder.toString();
    }
}
