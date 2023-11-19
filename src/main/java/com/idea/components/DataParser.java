package com.idea.components;

import com.idea.entity.VillagePart;
import com.idea.entity.Village;
import com.idea.repository.VillagePartRepository;
import com.idea.repository.VillageRepository;
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

    private final VillageRepository villageRepository;
    private final VillagePartRepository villagePartRepository;

    private final String villageTag = "vf:Obec";
    private final String villageCodeTag = "obi:Kod";
    private final String villageNameTag = "obi:Nazev";
    private final String villagePartTag = "vf:CastObce";
    private final String villagePartCodeTag = "coi:Kod";
    private final String villagePartNameTag = "coi:Nazev";
    private final String villagePartBelongToTag = "coi:Obec";

    @Autowired
    public DataParser(VillageRepository villageRepository, VillagePartRepository villagePartRepository) {
        this.villageRepository = villageRepository;
        this.villagePartRepository = villagePartRepository;
    }

    /**
     * Parses provided xml data and then saves it to the database.
     * @param unzippedData path to the xml file
     */
    public void parseXml(String unzippedData) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File inputFile = new File(unzippedData);
            Document document = builder.parse(inputFile);
            List<Village> villageList = parseVillageData(document);
            villageRepository.saveAll(villageList);
            List<VillagePart> villagePartList = parseVillagePartData(document);
            villagePartRepository.saveAll(villagePartList);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Parses village data from the provided XML document and creates a list of villages.
     *
     * @param document The XML document containing village data.
     * @return A list of Village objects representing villages parsed from the document.
     */
    private List<Village> parseVillageData(Document document) {
        List<Village> villageList = new ArrayList<>();
        NodeList villageNodes = document.getElementsByTagName(villageTag);
        for (int i = 0; i < villageNodes.getLength(); i++) {
            Node villageNode = villageNodes.item(i);
            if (villageNode.getNodeType() == Node.ELEMENT_NODE) {
                Element villageElement = (Element) villageNode;
                String code = villageElement.getElementsByTagName(villageCodeTag).item(0).getTextContent();
                String name = villageElement.getElementsByTagName(villageNameTag).item(0).getTextContent();
                Village village = new Village(code, name);
                villageList.add(village);
            }
        }
        return villageList;
    }

    /**
     * Parses village part data from the provided XML document and creates a list of village parts.
     * @param document The XML document containing village part data.
     * @return A list of VillagePart objects representing village parts parsed from the document.
     */
    private List<VillagePart> parseVillagePartData(Document document) {
        List<VillagePart> villagePartList = new ArrayList<>();
        NodeList villagePartNodes = document.getElementsByTagName(villagePartTag);
        for (int i = 0; i < villagePartNodes.getLength(); i++) {
            Node villagePartNode = villagePartNodes.item(i);
            if (villagePartNode.getNodeType() == Node.ELEMENT_NODE) {
                Element villagePartElement = (Element) villagePartNode;
                Element villageCodeElement = (Element) villagePartElement.getElementsByTagName(villagePartBelongToTag).item(0);

                String villagePartBelongToCode = villageCodeElement.getElementsByTagName(villageCodeTag).item(0).getTextContent();
                String code = villagePartElement.getElementsByTagName(villagePartCodeTag).item(0).getTextContent();
                String name = villagePartElement.getElementsByTagName(villagePartNameTag).item(0).getTextContent();

                Village village = villageRepository.findByCode(villagePartBelongToCode);
                VillagePart villagePart = new VillagePart(code, name, village);
                villagePartList.add(villagePart);
            }
        }
        return villagePartList;
    }
}
