package model.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.Comic;
import controller.Creator;
import controller.hierarchy.Publisher;
import controller.hierarchy.Series;
import controller.hierarchy.Volume;

public class ExactSearchTest {
    
    private static List<Comic> data;
    private static Searcher exactSearch;
    private String input;
    private String query;

    private static Comic comic1;
    private static Comic comic2;
    private static Comic comic3;
    private static Comic comic4;

    @BeforeAll
    public static void loadData(){
        data = new ArrayList<>();
        Series spiderSeries = new Series("The Amazing Spider-Man");
        spiderSeries.setPublisher(new Publisher("Marvel"));

        Series hulkSeries = new Series("The Incredible Hulk");
        hulkSeries.setPublisher(new Publisher("Marvel"));

        Series batSeries = new Series("Batman");
        batSeries.setPublisher(new Publisher(("DC")));

        comic1 = new Comic("Amazing Spider-man", 5, "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume(2, spiderSeries));
        comic1.addCreator(new Creator("Stan Lee"));
        
        comic2 = new Comic("The Amazing Spider-man", 33, "Amazing and a spider?! Now with a THE!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-05-22"), new Volume(2, spiderSeries));
        comic2.addCreator(new Creator("Lee"));

        comic3 = new Comic("Incredible Shulk", 22, "Green, mean, and he's really feeling it!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2024-02-29"), new Volume(5, hulkSeries));
        comic3.addCreator(new Creator("Stan Lee 2"));
        comic3.addCreator(new Creator("Stan Lee"));

        comic4 = new Comic("Spider-Man Vs Batman", 33, "The spider vs the man!", 
                    BigDecimal.valueOf(5.50), LocalDate.parse("2021-05-12"), new Volume(7, batSeries));
        comic4.addCreator(new Creator("Evil Stan Lee"));

        data.add(comic1);
        data.add(comic2);
        data.add(comic3);
        data.add(comic4);
        
        exactSearch = new ExactSearch();
    }

    @Test
    public void testSearchSeriesTitle(){
        //Setup
        input = "series_title";
        query = "The Amazing Spider-MAN";
        List<Comic> expected = new ArrayList<>();
        expected.add(comic1);
        expected.add(comic2);

        //Invoke
        List<Comic> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchIssueNumber(){
        //Setup
        input = "issue_number";
        query = "33";
        List<Comic> expected = new ArrayList<>();
        expected.add(comic2);
        expected.add(comic4);
        
        //Invoke
        List<Comic> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++){
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchStoryTitle(){
        //Setup
        input = "story_title";
        query = "spIDER-mAN vs batMAN";
        List<Comic> expected = new ArrayList<>();
        expected.add(comic4);
        
        //Invoke
        List<Comic> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchPublisher(){
        //Setup
        input = "publisher";
        query = "Marvel";
        List<Comic> expected = new ArrayList<>();
        expected.add(comic1);
        expected.add(comic2);
        expected.add(comic3);
        
        //Invoke
        List<Comic> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchDate(){
        //Setup
        input = "date";
        query = "2022-02-22";
        List<Comic> expected = new ArrayList<>();
        expected.add(comic1);
        
        //Invoke
        List<Comic> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchCreator(){
        //Setup
        input = "creator";
        query = "Stan Lee";
        List<Comic> expected = new ArrayList<>();
        expected.add(comic1);
        expected.add(comic3);
        
        //Invoke
        List<Comic> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
