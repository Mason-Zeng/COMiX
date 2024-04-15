package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

public class ComicOutput {
    private String series;
    private String issue;
    private String fullTitle;
    private String variantDescription;
    private String publisher;
    private String releaseDate;
    private String format;
    private String addedDate;
    private String creators;

    // Constructor, getters, and setters

    // Getters
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String string) {
        this.issue = string;
    }

    // private void setIssue(String string) {
    // }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getCreators() {
        return creators;
    }

    public void setCreators(String creators) {
        this.creators = creators;
    }

    @Override
    public String toString() {
        return "Comic {" +
                "series='" + series + '\'' +
                ", issue=" + issue +
                ", fullTitle='" + fullTitle + '\'' +
                ", variantDescription='" + variantDescription + '\'' +
                ", publisher='" + publisher + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", format='" + format + '\'' +
                ", addedDate='" + addedDate + '\'' +
                ", creators='" + creators + '\'' +
                '}';
    }

    public ComicOutput() {
    }


    public static List<ComicOutput> loadFromCSV(String csvFile){
        List<ComicOutput> comics = new ArrayList<>();
        try (CsvListReader reader = new CsvListReader(new BufferedReader(new FileReader(csvFile, StandardCharsets.UTF_8)), CsvPreference.STANDARD_PREFERENCE)){
            //Skips the header
            reader.read();

            List<String> columns;
            while ((columns = reader.read()) != null){
                ComicOutput comic = new ComicOutput();
                comic.setSeries(getValueOrDefault(columns.get(0)));
                comic.setIssue(getValueOrDefault(columns.get(1)));
                comic.setFullTitle(getValueOrDefault(columns.get(2)));
                comic.setVariantDescription(getValueOrDefault(columns.get(3)));
                comic.setPublisher(getValueOrDefault(columns.get(4)));
                comic.setReleaseDate(getValueOrDefault(columns.get(5)));
                comic.setFormat(getValueOrDefault(columns.get(6)));
                comic.setAddedDate(getValueOrDefault(columns.get(7)));
                comic.setCreators(getValueOrDefault(columns.get(8)));

                // Add the Comic object to the ArrayList
                comics.add(comic);
            }
            
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return comics;
    }

    
    // Helper method to get value from array with default if index is out of bounds
    //this is just for in case there is an error getting values/attributes for the comic
    private static String getValueOrDefault(String value) {
        return (value != null) ? value.trim() : "N/A";
    }
    

    public static void main(String[] args) {
        
        System.out.println(ComicOutput.loadFromCSV("data/comics-old.csv"));
        }

}
