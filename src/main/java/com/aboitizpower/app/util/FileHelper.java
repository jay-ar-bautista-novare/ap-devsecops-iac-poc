package com.aboitizpower.app.util;

import com.aboitizpower.app.exception.DevSecOpsException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileHelper {

    public static final String NEW_LINE = "\n";
    public static final String BIN_BASH = "#!/bin/bash";
    public static final String INSTALL_DOCKER_PATH = "src/main/sh/install_docker.sh";

    private FileHelper(){
    }

    public static String retrieveFileContent(Path path) {
        String content;
        try(Stream<String> lines = Files.lines(path)){
            content = lines.collect(Collectors.joining(NEW_LINE));
        } catch (IOException e) {
            throw new DevSecOpsException("Error encountered in getting Installer", e);
        }
        return content;
    }
}
