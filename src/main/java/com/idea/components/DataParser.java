package com.idea.components;

import com.idea.entity.CastObce;
import com.idea.entity.Obec;
import com.idea.repository.CastObceRepository;
import com.idea.repository.ObecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataParser {

    private final ObecRepository villageRepository;
    private final CastObceRepository villagePartRepository;

    @Autowired
    public DataParser(ObecRepository villageRepository, CastObceRepository villagePartRepository) {
        this.villageRepository = villageRepository;
        this.villagePartRepository = villagePartRepository;
    }

    public void parseXml(String unzippedData) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File inputFile = new File(unzippedData);
            Document document = builder.parse(inputFile);
            List<Obec> villageList = parseVillageData(document);
            villageRepository.saveAll(villageList);
            List<CastObce> villagePartList = parseVillagePartData(document);
            villagePartRepository.saveAll(villagePartList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private List<Obec> parseVillageData(Document document) {
        String villageTag = "vf:Obec";
        String villageCodeTag = "obi:Kod";
        String villageNameTag = "obi:Nazev";

        List<Obec> villageList = new ArrayList<>();
        NodeList villageNodes = document.getElementsByTagName(villageTag);
        for (int i = 0; i < villageNodes.getLength(); i++) {
            Node villageNode = villageNodes.item(i);
            if (villageNode.getNodeType() == Node.ELEMENT_NODE) {
                Element villageElement = (Element) villageNode;
                String code = villageElement.getElementsByTagName(villageCodeTag).item(0).getTextContent();
                String name = villageElement.getElementsByTagName(villageNameTag).item(0).getTextContent();
                Obec village = new Obec(code, name);
                villageList.add(village);
            }
        }
        return villageList;
    }

    private List<CastObce> parseVillagePartData(Document document) {
        String villagePartTag = "vf:CastObce";
        String villagePartCodeTag = "coi:Kod";
        String villagePartNameTag = "coi:Nazev";
        String villagePartBelongToTag = "coi:Obec";
        String villageCodeTag = "obi:Kod";

        List<CastObce> villagePartList = new ArrayList<>();
        NodeList villagePartNodes = document.getElementsByTagName(villagePartTag);
        for (int i = 0; i < villagePartNodes.getLength(); i++) {
            Node villagePartNode = villagePartNodes.item(i);
            if (villagePartNode.getNodeType() == Node.ELEMENT_NODE) {
                Element villagePartElement = (Element) villagePartNode;
                Element villageCodeElement = (Element) villagePartElement.getElementsByTagName(villagePartBelongToTag).item(0);

                String villagePartBelongToCode = villageCodeElement.getElementsByTagName(villageCodeTag).item(0).getTextContent();
                String code = villagePartElement.getElementsByTagName(villagePartCodeTag).item(0).getTextContent();
                String name = villagePartElement.getElementsByTagName(villagePartNameTag).item(0).getTextContent();

                Obec village = villageRepository.findByCode(villagePartBelongToCode);
                CastObce villagePart = new CastObce(code, name, village);
                villagePartList.add(villagePart);
            }
        }
        return villagePartList;
    }
}
