package controller.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Comic;
import model.Creator;

public class PartialSearch implements Searcher{

    @Override
    public List<Comic> searchData(String query, List<Comic> data, String input) {
        query = query.toLowerCase();
        List<Comic> comics = new ArrayList<>();
        switch(input){

            case "series_title":
            for (Comic comic : data){
                String series = comic.getSeriesTitle();
                series = series.toLowerCase();
                if (series.contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "issue_number":
            for (Comic comic: data){
                int issueNumber = comic.getIssueNumber();
                if (String.valueOf(issueNumber).contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "story_title":
            for (Comic comic: data){
                String title = comic.getTitle();
                title = title.toLowerCase();
                if (title.contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "publisher":
            for (Comic comic : data){
                String name = comic.getPublisherName();
                name = name.toLowerCase();
                if (name.contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "date":
            for (Comic comic : data){
                LocalDate date = comic.getDate();
                if (date.toString().contains(query)){
                    comics.add(comic);
                }
            }
            break;

            case "creator":
            for (Comic comic : data){
                List<Creator> creators = comic.getCreators();
                for (Creator creator : creators){
                    String name = creator.getName();
                    name = name.toLowerCase();
                    if (name.contains(query)){
                        comics.add(comic);
                        break;
                    }
                }
            }
            break;
        }

        return comics;
    }
    
    // Any 2 instances of Partial Search are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PartialSearch) ? true : false;
    }

}
