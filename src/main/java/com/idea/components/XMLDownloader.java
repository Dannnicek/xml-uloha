package com.idea.components;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipInputStream;

@Component
public class XMLDownloader {

    /**
     * Downloads a file from the provided url and saves it to the provided path.
     * @param downloadedData path to the downloaded data file
     */
    public void downloadXml(URL url, String downloadedData) {
        try {
            Path destinationPath = Paths.get(downloadedData);
            Files.createDirectories(destinationPath.getParent());
            try (InputStream in = url.openStream()) {
                Files.copy(in, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Unzips data from a specific file and saves the unzipped data to another specific file.
     * @param downloadedData path to the downloaded data file
     * @param unzippedData path to the unzipped data file
     */
    public void unzipData(String downloadedData, String unzippedData) throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(downloadedData));
        zis.getNextEntry();
        Path newFilePath = Paths.get(unzippedData);
        try (FileOutputStream fos = new FileOutputStream(newFilePath.toFile())) {
            int length;
            while ((length = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
        zis.closeEntry();
        zis.close();
    }
}
