package com.idea;

import com.idea.components.DataParser;
import com.idea.components.XMLDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class XMLService {

    private final XMLDownloader xmlDownloader;
    private final DataParser dataParser;

    private final String urlForDownload = "https://www.smartform.cz/download/kopidlno.xml.zip";
    private final String downloadedDataPath = "data/downloadedData.zip";
    private final String unzippedDataPath = "data/unzippedData.xml";

    @Autowired
    public XMLService(XMLDownloader xmlDownloader, DataParser dataParser) {
        this.xmlDownloader = xmlDownloader;
        this.dataParser = dataParser;
    }

    public void processData() throws MalformedURLException {
        URL url = new URL(urlForDownload);
        String downloadedData = downloadedDataPath;
        String unzippedData = unzippedDataPath;
        xmlDownloader.downloadXml(url, downloadedData);
        try {
            xmlDownloader.unzipData(downloadedData, unzippedData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dataParser.parseXml(unzippedData);
    }
}
