package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    public static List<ComicOutput> loadFromCSV(String csvFile) {
        List<ComicOutput> comics = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into fields using comma as the delimiter
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // Split by comma, ignoring commas within quotes

                // Create a Comic object and populate its attributes
                ComicOutput comic = new ComicOutput();
                comic.setSeries(getValueOrDefault(data, 0));
                comic.setIssue(getValueOrDefault(data, 1));
                comic.setFullTitle(getValueOrDefault(data, 2));
                comic.setVariantDescription(getValueOrDefault(data, 3));
                comic.setPublisher(getValueOrDefault(data, 4));
                comic.setReleaseDate(getValueOrDefault(data, 5));
                comic.setFormat(getValueOrDefault(data, 6));
                comic.setAddedDate(getValueOrDefault(data, 7));
                comic.setCreators(getValueOrDefault(data, 8));

                // Add the Comic object to the ArrayList
                comics.add(comic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comics;
    }

    // Helper method to get value from array with default if index is out of bounds
    //this is just for in case there is an error getting values/attributes for the comic
    private static String getValueOrDefault(String[] array, int index) {
        return (index >= 0 && index < array.length) ? array[index].trim() : "N/A";
    }

    // Main method for testing
    public static void main(String[] args) {
        String csvFile = "data/comics.csv";
        List<ComicOutput> comics = ComicOutput.loadFromCSV(csvFile);
        
        // Print the contents of the comics item by item
        // for (ComicOutput comic : comics) 
        // {
        //     System.out.println("\n");
        //     System.out.println(comic);
        // }

        System.out.println(comics); //simple total printing 
        
        //this is an example for if we need to reach certain records

        // Iterator<ComicOutput> iterator = comics.iterator();
        // while (iterator.hasNext()) {
        //     ComicOutput comic = iterator.next();
        //     // Access individual ComicOutput object
        //     // Example: Access publisher of each comic
        //     String publisher = comic.getPublisher();
        //     System.out.println("Publisher: " + publisher);
        // }

        
    }

}
