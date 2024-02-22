package controller.search;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.Comic;
import controller.Creator;

public class PartialSearch implements Searcher{

    @Override
    public List<Comic> searchData(String query, List<Comic> data, String input) {
        query = query.toLowerCase();
        List<Comic> comics = new ArrayList<>();
        switch(input){

            case "series_title":
            throw new UnsupportedOperationException("Incomplete method, missing series title in Comic class");

            case "issue_number":
            for (Comic comic: data){
                int issueNumber = comic.getIssueNumber();
                if (query.contains(String.valueOf(issueNumber))){
                    comics.add(comic);
                }
            }

            case "story_title":
            for (Comic comic: data){
                String title = comic.getTitle();
                if (title.contains(query)){
                    comics.add(comic);
                }
            }

            case "publisher":
            throw new UnsupportedOperationException("Incomplete method, missing publisher in Comic class");

            case "date":
            for (Comic comic : data){
                LocalDate date = comic.getDate();
                if (query.contains(date.toString())){
                    comics.add(comic);
                }
            }

            case "creator":
            for (Comic comic : data){
                List<Creator> creators = comic.getCreators();
                for (Creator creator : creators){
                    if (query.contains(creator.getName())){
                        comics.add(comic);
                        break;
                    }
                }
            }
        }

        return comics;
    }
    
    // Any 2 instances of Partial Search are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PartialSearch) ? true : false;
    }

}
