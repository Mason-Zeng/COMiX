package controller.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.Comic;

public class ExactSearchTest {
    
    private static List<Comic> data;
    private static Searcher exactSearch;
    private String input;
    private String query;

    @BeforeAll
    public static void loadData(){
        data = new ArrayList<>();
        // TODO add comics into data once Comic class is done
        
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
