package model.foreign.exporting;

import java.io.FileWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Creator;
import model.foreign.DataExporter;
import model.marking.Marking;
import model.marking.MarkingFactory;

public class XMLDataExporter implements DataExporter {
    
    private static TransformerFactory transformerFactory = TransformerFactory.newInstance();
    private static   DocumentBuilderFactory docBuilder = DocumentBuilderFactory.newInstance();

    private Element convertToElement(Document doc, Marking mark) {
        Element comicElement = doc.createElement("comic");
        
        Element title = doc.createElement("title");
        title.setTextContent(mark.getTitle());
        comicElement.appendChild(title);
        
        Element description = doc.createElement("description");
        description.setTextContent(mark.getDescription());
        comicElement.appendChild(description);
        
        Element issue = doc.createElement("issue");
        issue.setTextContent(mark.getIssueNumber());
        comicElement.appendChild(issue);
        
        Element volume = doc.createElement("volume");
        volume.setTextContent(mark.getVolumeNumber());
        comicElement.appendChild(volume);
        
        Element series = doc.createElement("series");
        series.setTextContent(mark.getSeriesTitle());
        comicElement.appendChild(series);
        
        Element publisher = doc.createElement("publisher");
        publisher.setTextContent(mark.getPublisherName());
        comicElement.appendChild(publisher);
        
        Element value = doc.createElement("value");
        value.setTextContent(mark.getTrueValue().toString());
        comicElement.appendChild(value);

        Element date = doc.createElement("date");
        date.setTextContent(mark.getDate().toString());
        comicElement.appendChild(date);
        
        Element creators = doc.createElement("creators");
        for (Creator creator : mark.getCreators()) {
            Element creatorElement = doc.createElement("creator");
            creatorElement.setTextContent(creator.getName());
            creators.appendChild(creatorElement);
        }
        comicElement.appendChild(creators);

        Element format = doc.createElement("format");
        format.setTextContent(MarkingFactory.getFormat(mark));
        comicElement.appendChild(format);

        return comicElement;
    }

    private void write(Document doc, FileWriter file) throws TransformerException {
        StreamResult stream = new StreamResult(file);
       
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        transformer.transform(source, stream);
    }


    @Override
    public void exportFile(List<Marking> data, FileWriter file) {
        try {
            Document doc = docBuilder.newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement("collection");
            doc.appendChild(rootElement);
            for (Marking mark : data) {
                rootElement.appendChild(convertToElement(doc, mark));
            }
            write(doc, file);
        } 
        catch (ParserConfigurationException e) {} 
        catch (TransformerException e) {}
    }
}
