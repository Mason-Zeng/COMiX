package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.Comic;
import controller.Creator;
import controller.hierarchy.Publisher;
import controller.hierarchy.Series;
import controller.hierarchy.Volume;
import model.search.ExactSearch;
import model.search.PartialSearch;
import model.search.Searcher;
import model.sort.DefaultSorter;
import model.sort.PublicationDateSorter;

public class ComicSearcherTest {

    public static List<Comic> data;

    private static Comic comic1;
    private static Comic comic2;
    private static Comic comic3;
    private static Comic comic4;

    @BeforeAll
    public static void loadComicData(){
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
        
        comic2 = new Comic("The Amazing Spider-man", 3, "Amazing and a spider?! Now with a THE!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2022-05-22"), new Volume(2, spiderSeries));
        comic2.addCreator(new Creator("Lee"));

        comic3 = new Comic("Incredible Shulk", 32, "Green, mean, and he's really feeling it!", 
                    BigDecimal.valueOf(32.59), LocalDate.parse("2024-02-29"), new Volume(5, hulkSeries));
        comic3.addCreator(new Creator("Stan Lee 2"));
        comic3.addCreator(new Creator("Stan Lee"));

        comic4 = new Comic("Spider-Man Vs Batman", 13, "The spider vs the man!", 
                    BigDecimal.valueOf(5.50), LocalDate.parse("2021-05-12"), new Volume(7, batSeries));
        comic4.addCreator(new Creator("Evil Stan Lee"));

        data.add(comic1);
        data.add(comic2);
        data.add(comic3);
        data.add(comic4);
    }

    @Test
    public void testSetSorterToPubDate(){
        //Setup
        Comparator<Comic> sorter = new PublicationDateSorter();
        Comparator<Comic> expected = sorter;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        comicSearcher.setSorter(sorter);
        Comparator<Comic> actual = comicSearcher.getSorter();
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testDefaultSorter(){
        //Setup
        Comparator<Comic> sorter = new DefaultSorter();
        Comparator<Comic> expected = sorter;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        Comparator<Comic> actual = comicSearcher.getSorter();
        
        //Analyze
        assertEquals(expected, actual);
    }


    @Test
    public void testSetSorterToDefault(){
        //Setup
        Comparator<Comic> sorter1 = new PublicationDateSorter();
        Comparator<Comic> sorter2 = new DefaultSorter();
        Comparator<Comic> expected = sorter2;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        comicSearcher.setSorter(sorter1);
        comicSearcher.setSorter(sorter2);
        Comparator<Comic> actual = comicSearcher.getSorter();
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testUnequalSorters(){
        //Setup
        Comparator<Comic> sorter1 = new PublicationDateSorter();
        Comparator<Comic> sorter2 = new DefaultSorter();
        Comparator<Comic> unexpected = sorter2;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        comicSearcher.setSorter(sorter1);
        Comparator<Comic> actual = comicSearcher.getSorter();
        
        //Analyze
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void testSetSearcherToExact(){
        //Setup
        Searcher searcher = new PartialSearch();
        Searcher expected = searcher;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        Searcher actual = comicSearcher.getSearcher();
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testDefaultSearcher(){
        //Setup
        Searcher searcher = new ExactSearch();
        Searcher expected = searcher;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        comicSearcher.setSearcher(searcher);
        Searcher actual = comicSearcher.getSearcher();
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testSetSearcherToPartial(){
        //Setup
        Searcher searcher1 = new ExactSearch();
        Searcher searcher2 = new PartialSearch();
        Searcher expected = searcher2;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        comicSearcher.setSearcher(searcher1);
        comicSearcher.setSearcher(searcher2);
        Searcher actual = comicSearcher.getSearcher();
        
        //Analyze
        assertEquals(expected, actual);
    }

    @Test
    public void testUnequalSearchers(){
        //Setup
        Searcher searcher1 = new ExactSearch();
        Searcher searcher2 = new PartialSearch();
        Searcher unexpected = searcher2;
        ComicSearcher comicSearcher = new ComicSearcher(data);
        
        //Invoke
        comicSearcher.setSearcher(searcher1);
        Searcher actual = comicSearcher.getSearcher();
        
        //Analyze
        assertNotEquals(unexpected, actual);
    }

    @Test
    public void testSearchPartialDefaultSort(){
        //Setup
        ComicSearcher comicSearcher = new ComicSearcher(data);
        List<Comic> expected = new ArrayList<>();
        expected.add(comic2);
        expected.add(comic1);
        
        //Invoke
        List<Comic> actual = comicSearcher.search("Spider-MAN", "series_title");
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test 
    public void testSearchPartialPubDateSort(){
       //Setup
       ComicSearcher comicSearcher = new ComicSearcher(data);
       comicSearcher.setSorter(new PublicationDateSorter());
       List<Comic> expected = new ArrayList<>();
       expected.add(comic1);
       expected.add(comic2);
       
       //Invoke
       List<Comic> actual = comicSearcher.search("Spider-MAN", "series_title");
       
       //Analyze
       assertEquals(expected.size(), actual.size());
       for (int i = 0; i < expected.size(); i++) {
           assertEquals(expected.get(i), actual.get(i));
       }
    }

    @Test
    public void testSearchExactDefaultSort(){
        //Setup
        ComicSearcher comicSearcher = new ComicSearcher(data);
        comicSearcher.setSearcher(new ExactSearch());
        List<Comic> expected = new ArrayList<>();
        expected.add(comic2);
        expected.add(comic1);
        
        //Invoke
        List<Comic> actual = comicSearcher.search("The amazing Spider-MAN", "series_title");
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testSearchExactPubDateSort(){
        //Setup
        ComicSearcher comicSearcher = new ComicSearcher(data);
        comicSearcher.setSearcher(new ExactSearch());
        comicSearcher.setSorter(new PublicationDateSorter());
        List<Comic> expected = new ArrayList<>();
        expected.add(comic1);
        expected.add(comic2);
        
        //Invoke
        List<Comic> actual = comicSearcher.search("The amazing Spider-MAN", "series_title");
        
        //Analyze
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
