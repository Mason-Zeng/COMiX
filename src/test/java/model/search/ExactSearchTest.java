package model.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.Comic;
import controller.hierarchy.Volume;

public class ExactSearchTest {
    
    private static List<Comic> data;
    private static Searcher exactSearch;
    private String input;
    private String query;

    @BeforeAll
    public static void loadData(){
        data = new ArrayList<>();
        // TODO add comics into data once Comic class is done
        data.add(new Comic("Amazing Spider-man", 5, "Amazing and a spider?!", 
                BigDecimal.valueOf(32.59), LocalDate.of(2022, 2, 22), new Volume(2)));
        data.add(new Comic("The Amazing Spider-man", 33, "Amazing and a spider?! Now with a THE!", 
                BigDecimal.valueOf(32.59), LocalDate.of(2022, 5, 22), new Volume(2)));
        data.add(new Comic("Incredible Shulk", 22, "Green, mean, and he's really feeling it!", 
                BigDecimal.valueOf(32.59), LocalDate.of(2022, 2, 29), new Volume(5)));
        
        exactSearch = new ExactSearch();
    }

    @Test
    public void testSearchSeriesTitle(){
        //Setup
        input = "series_title";
        query = "The Amazing Spider-MAN";
        List<Comic> expected = new ArrayList<>();
        //TODO fill expected with expected comics when Comic class is done
        
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
        query = "spIDER-mAN vs the hulk";
        List<Comic> expected = new ArrayList<>();
        
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
        //TODO fix this with proper Date format
        query = "2-22-22";
        List<Comic> expected = new ArrayList<>();
        
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
        
        //Invoke
        List<Comic> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
