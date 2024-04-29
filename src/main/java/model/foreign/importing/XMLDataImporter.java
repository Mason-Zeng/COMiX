package model.foreign.importing;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import model.Character;
import model.Comic;
import model.Creator;
import model.foreign.DataImporter;
import model.hierarchy.Publisher;
import model.hierarchy.Series;
import model.hierarchy.Volume;
import model.marking.Marking;
import model.marking.MarkingHandler;

public class XMLDataImporter implements DataImporter {

    private static DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();

    @Override
    public List<Marking> importFile(FileReader file) {
        InputSource source = new InputSource(file);
        List<Marking> result = new ArrayList<>();
        try {
            Document doc = fac.newDocumentBuilder().parse(source);
            NodeList nodeList = doc.getElementsByTagName("comic");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element comicElement = (Element)node;
                    String title = comicElement.getElementsByTagName("title").item(0).getTextContent();
                    String description = comicElement.getElementsByTagName("description").item(0).getTextContent();
                    String issue = comicElement.getElementsByTagName("issue").item(0).getTextContent();
                    Publisher publisher = new Publisher(comicElement.getElementsByTagName("publisher").item(0).getTextContent());
                    Series series = new Series(comicElement.getElementsByTagName("series").item(0).getTextContent(), publisher);
                    Volume volume = new Volume(comicElement.getElementsByTagName("volume").item(0).getTextContent(), series);
                    BigDecimal value = new BigDecimal(comicElement.getElementsByTagName("value").item(0).getTextContent());
                    LocalDate date = LocalDate.parse(comicElement.getElementsByTagName("date").item(0).getTextContent());

                    value = (value.equals(BigDecimal.valueOf(0))) ? BigDecimal.valueOf(1.00) : value;

                    Marking comic = new Comic(title, issue, description, value, date);
                    comic.setVolume(volume);

                    NodeList creatorList = comicElement.getElementsByTagName("creator");
                    for (int j = 0; j < creatorList.getLength(); j++) {
                        comic.addCreator(new Creator(creatorList.item(j).getTextContent()));
                    }
                    NodeList characterList = comicElement.getElementsByTagName("character");
                    for (int j = 0; j < characterList.getLength(); j++) {
                        comic.addCharacter(new Character(characterList.item(j).getTextContent()));
                    }

                    String format = comicElement.getElementsByTagName("format").item(0).getTextContent();
                    comic = MarkingHandler.formatComic(comic, format);
                    result.add(comic);
                }
            }
        } catch (SAXException e) {} 
        catch (IOException e) {} 
        catch (ParserConfigurationException e) {}
        return result;
    }

}