package controller.search;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.sort.IssueNumberSorter;
import model.hierarchy.Publisher;
import model.hierarchy.Series;
import model.hierarchy.Volume;
import model.marking.Authenticate;
import model.marking.Grade;
import model.marking.Marking;
import model.Comic;
import model.Creator;
import model.marking.Sign;
import model.marking.Slab;


public class ExactSearchTest {
    
    private static List<Marking> data;
    private static Searcher exactSearch;
    private String input;
    private String query;

    private static Marking comic1;
    private static Marking comic2;
    private static Marking comic3;
    private static Marking comic4;
    private static Marking comicTest;

    @BeforeAll
    public static void loadData(){
        data = new ArrayList<>();
        Series spiderSeries = new Series("The Amazing Spider-Man");
        spiderSeries.setPublisher(new Publisher("Marvel"));

        Series hulkSeries = new Series("The Incredible Hulk");
        hulkSeries.setPublisher(new Publisher("Marvel"));

        Series batSeries = new Series("Batman");
        batSeries.setPublisher(new Publisher(("DC")));

        comic1 = new Comic("Amazing Spider-man", "5", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        comic1.addCreator(new Creator("Stan Lee"));
        
        comic2 = new Comic("The Amazing Spider-man", "33", "Amazing and a spider?! Now with a THE!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-05-22"), new Volume("2", spiderSeries));
        comic2.addCreator(new Creator("Lee"));

        comic3 = new Comic("Incredible Shulk", "22", "Green, mean, and he's really feeling it!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2024-02-29"), new Volume("5", hulkSeries));
        comic3.addCreator(new Creator("Stan Lee 2"));
        comic3.addCreator(new Creator("Stan Lee"));

        comic4 = new Comic("Spider-Man Vs Batman", "33", "The spider vs the man!", 
                    BigDecimal.valueOf(5.50), LocalDate.parse("2021-05-12"), new Volume("7", batSeries));
        comic4.addCreator(new Creator("Evil Stan Lee"));

        data.add(comic1);
        data.add(comic2);
        data.add(comic3);
        data.add(comic4);
        comicTest = new Sign(comic4);
        comicTest = new Authenticate(comicTest);
        comicTest= new Grade(comicTest, 3);
        comicTest = new Slab(comicTest);
        data.add(comicTest);

        
        exactSearch = new ExactSearch();
    }

    @Test
    public void testSearchSeriesTitle(){
        //Setup
        input = "series_title";
        query = "The Amazing Spider-MAN";
        List<Marking> expected = new ArrayList<>();
        expected.add(comic1);
        expected.add(comic2);

        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
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
        List<Marking> expected = new ArrayList<>();
        expected.add(comic2);
        expected.add(comic4);
        expected.add(comicTest);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
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
        List<Marking> expected = new ArrayList<>();
        expected.add(comic4);
        expected.add(comicTest);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
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
        List<Marking> expected = new ArrayList<>();
        expected.add(comic1);
        expected.add(comic2);
        expected.add(comic3);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
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
        List<Marking> expected = new ArrayList<>();
        expected.add(comic1);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
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
        List<Marking> expected = new ArrayList<>();
        expected.add(comic1);
        expected.add(comic3);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    
    @Test
    public void testSearchSign(){
        //Setup
        input = "sign";
        query = "Batman";
        List<Marking> expected = new ArrayList<>();
        expected.add(comicTest);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
    
    @Test
    public void testSearchAuthenticte(){
        //Setup
        input = "authenticate";
        query = "Batman";
        List<Marking> expected = new ArrayList<>();
        expected.add(comicTest);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchGrade(){
        //Setup
        input = "grade";
        query = "Batman";
        List<Marking> expected = new ArrayList<>();
        expected.add(comicTest);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchSlab(){
        //Setup
        input = "slab";
        query = "Batman";
        List<Marking> expected = new ArrayList<>();
        expected.add(comicTest);
        
        //Invoke
        List<Marking> actual = exactSearch.searchData(query, data, input);
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testRuns(){
        List<Marking> list = new ArrayList<>();
        Series spiderSeries = new Series("The Amazing Spider-Man");
        Marking comicRun1 = new Comic("Amazing Spider-man", "2", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun16 = new Comic("Amazing Spider-man", "1", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun2 = new Comic("Amazing Spider-man", "3", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun3 = new Comic("Amazing Spider-man", "4", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun4 = new Comic("Amazing Spider-man", "5", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun5 = new Comic("Amazing Spider-man", "6", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun6 = new Comic("Amazing Spider-man", "7", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun7 = new Comic("Amazing Spider-man", "14", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun8 = new Comic("Amazing Spider-man", "9", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun9 = new Comic("Amazing Spider-man", "10", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun10 = new Comic("Amazing Spider-man", "13", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun11 = new Comic("Amazing Spider-man", "12", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun12 = new Comic("Amazing Spider-man", "11", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun13 = new Comic("Amazing Spider-man", "8", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun14 = new Comic("Amazing Spider-man", "15", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        Marking comicRun15 = new Comic("Amazing Spider-man", "16", "Amazing and a spider?!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-02-22"), new Volume("2", spiderSeries));
        list.add(comicRun1);
        list.add(comicRun2);
        list.add(comicRun3);
        list.add(comicRun4);
        list.add(comicRun5);
        list.add(comicRun6);
        list.add(comicRun7);
        list.add(comicRun8);
        list.add(comicRun9);
        list.add(comicRun10);
        list.add(comicRun11);
        list.add(comicRun12);
        list.add(comicRun13);
        list.add(comicRun14);
        list.add(comicRun15);
        list.add(comicRun16);

        /* 
        Collections.sort(list, new IssueNumberSorter());
        for(Marking x : list){
            System.out.println(x);
        }
        */
        input = "runs";
        query = "Amazing Spider-man";
        List<Marking> actual = exactSearch.searchData(query, list, input);
        for(Marking x : actual){
            System.out.println(x);
        }
    }
}
