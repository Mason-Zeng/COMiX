package model;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class Comic {
//     private String series;
//     private int issue;
//     private String fullTitle;
//     private String variantDescription;
//     private String publisher;
//     private String releaseDate;
//     private String format;
//     private String addedDate;
//     private String creators;

//     // Constructor, getters, and setters
//     // Implement these according to your requirements

//     public Comic() {
//     }

//     // Getters

//     public String getSeries() {
//         return series;
//     }

//     public int getIssue() {
//         return issue;
//     }

//     public String getFullTitle() {
//         return fullTitle;
//     }

//     public String getVariantDescription() {
//         return variantDescription;
//     }

//     public String getPublisher() {
//         return publisher;
//     }

//     public String getReleaseDate() {
//         return releaseDate;
//     }

//     public String getFormat() {
//         return format;
//     }

//     public String getAddedDate() {
//         return addedDate;
//     }

//     public String getCreators() {
//         return creators;
//     }

//     // Setters

//     public void setSeries(String series) {
//         this.series = series;
//     }

//     public void setIssue(int issue) {
//         this.issue = issue;
//     }

//     public void setFullTitle(String fullTitle) {
//         this.fullTitle = fullTitle;
//     }

//     public void setVariantDescription(String variantDescription) {
//         this.variantDescription = variantDescription;
//     }

//     public void setPublisher(String publisher) {
//         this.publisher = publisher;
//     }

//     public void setReleaseDate(String releaseDate) {
//         this.releaseDate = releaseDate;
//     }

//     public void setFormat(String format) {
//         this.format = format;
//     }

//     public void setAddedDate(String addedDate) {
//         this.addedDate = addedDate;
//     }

//     public void setCreators(String creators) {
//         this.creators = creators;
//     }

// //     @Override
// // public String toString() {
// //     return "Comic{" +
// //             "series='" + series + '\'' +
// //             ", issue=" + issue +
// //             ", fullTitle='" + fullTitle + '\'' +
// //             ", variantDescription='" + variantDescription + '\'' +
// //             ", publisher='" + publisher + '\'' +
// //             ", releaseDate='" + releaseDate + '\'' +
// //             ", format='" + format + '\'' +
// //             ", addedDate='" + addedDate + '\'' +
// //             ", creators='" + creators + '\'' +
// //             '}';
// // }

// @Override
// public String toString() {
//     return "Comic{" +
//             "series='" + series + '\'' +
//             ", issue=" + issue +
//             ", fullTitle='" + fullTitle + '\'' +
//             ", variantDescription='" + variantDescription + '\'' +
//             ", publisher='" + publisher + '\'' +
//             ", releaseDate='" + releaseDate + '\'' +
//             ", format='" + format + '\'' +
//             ", addedDate='" + addedDate + '\'' +
//             ", creators='" + creators + '\'' +
//             '}';
// }

//     public static void main(String[] args) {
//         String csvFile = "data/comics.csv";
//         String line;
//         String csvSplitBy = ","; // Assuming tab-separated values
    
//         List<Comic> comics = new ArrayList<>();
    
//         try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//             // Skip the header line
//             br.readLine();
    
//             while ((line = br.readLine()) != null) {
//                 // Split the line into fields
//                 String[] data = line.split(csvSplitBy);
    
//                 // Check if the split data has enough elements
//                 if (data.length < 8) {
//                     // Log an error or handle the incomplete line appropriately
//                     // System.err.println("Incomplete data: " + line);
//                     continue; // Skip this line and proceed to the next one
//                 }
    
//                 // Create a Comic object and populate its attributes
//                 Comic comic = new Comic();
//                 comic.setSeries(data[0]);
//                 try {
//                     comic.setIssue(Integer.parseInt(data[1]));
//                 } catch (NumberFormatException e) {
//                     // System.err.println("Invalid issue number: " + data[1]);
//                     continue; // Skip this line and proceed to the next one
//                 }
//                 comic.setFullTitle(data[2]);
//                 comic.setVariantDescription(data[3]);
//                 comic.setPublisher(data[4]);
//                 comic.setReleaseDate(data[5]);
//                 comic.setFormat(data[6]);
//                 comic.setAddedDate(data[7]);
//                 comic.setCreators(data[8]);
    
//                 // Add the Comic object to the ArrayList
//                 comics.add(comic);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
    
//         // Print the contents of the comics ArrayList
//         for (Comic comic : comics) {
//             System.out.println(comic);
//         }
    
//         // Now comics ArrayList contains all the Comic objects from the CSV file
//         // You can use it as needed
//     }
    



// }


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Comic {
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
    // Implement these according to your requirements

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
        return "Comic{" +
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

    public Comic() {
    }

    // Getters and setters
    // Implement these according to your requirements

    public static List<Comic> loadFromCSV(String csvFile) {
        List<Comic> comics = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into fields
                String[] data = line.split(",");

                // Check if the split data has enough elements
                if (data.length < 8) {
                    // Log an error or handle the incomplete line appropriately
                    continue; // Skip this line and proceed to the next one
                }

                // Create a Comic object and populate its attributes
                Comic comic = new Comic();
                comic.setSeries(data[0]);

                // Parse and set the issue number without quotes
                String issueNumber = data[1].replaceAll("\"", "").trim();
                comic.setIssue(issueNumber);

                comic.setFullTitle(data[2]);
                comic.setVariantDescription(data[3]);
                comic.setPublisher(data[4]);

                // Concatenate releaseDate and format into a single releaseDate attribute
                String releaseDate = data[5] + ", " + data[6].trim();
                comic.setReleaseDate(releaseDate);

                comic.setFormat(data[7]);

                // Set the addedDate and creators with appropriate values
                comic.setAddedDate(data[8]);  // Check if this index is correct
                // comic.setCreators(data[8]);   // Check if this index is correct

                // Add the Comic object to the ArrayList
                comics.add(comic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comics;
    }

    // Main method for testing
    public static void main(String[] args) {
        String csvFile = "data/comics.csv";
        List<Comic> comics = Comic.loadFromCSV(csvFile);

        // Print the contents of the comics list
        for (Comic comic : comics) {
            System.out.println(comic);
        }

        // Now comics list contains all the Comic objects from the CSV file
        // You can use it as needed
    }

}
