package controller.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.Comic;
import controller.Creator;

public class ExactSearch implements Searcher{

    @Override
    public List<Comic> searchData(String query, List<Comic> data, String input) {
        query = query.toLowerCase();
        List<Comic> comics = new ArrayList<>();

        switch (input){
            
            case "series_title":
            for (Comic comic : data){
                String series = comic.getSeriesTitle();
                if (query.equals(series)){
                    comics.add(comic);
                }
            }

            case "issue_number":
            for (Comic comic: data){
                int issue_number = comic.getIssueNumber();
                if (query.equals(String.valueOf(issue_number))){
                    comics.add(comic);
                }
            }

            case "story_title":
            for (Comic comic: data){
                String title = comic.getTitle();
                if (query.equals(title)){
                    comics.add(comic);
                }
            }

            case "publisher":
            for (Comic comic : data){
                String name = comic.getPublisherName();
                if (query.equals(name)){
                    comics.add(comic);
                }
            }

            case "date":
            for (Comic comic : data){
                LocalDate date = comic.getDate();
                if (query.equals(date.toString())){
                    comics.add(comic);
                }
            }

            case "creator":
            for (Comic comic : data){
                List<Creator> creators = comic.getCreators();
                for (Creator creator: creators){
                    if (query.equals(creator.getName())){
                        comics.add(comic);
                        break;
                    }
                }
            }
        }

        return comics;
    }
    
    // Any 2 instances of ExactSearch are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ExactSearch) ? true : false;
    }

}
