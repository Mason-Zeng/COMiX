package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.search.ExactSearch;
import controller.search.PartialSearch;
import controller.search.Searcher;
import controller.sort.DefaultSorter;
import controller.sort.PublicationDateSorter;

public class ComicSearcherTest {

    public static List<Comic> data;

    @BeforeAll
    public static void loadComicData(){
        data = new ArrayList<>();
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
}
