package controller.sort;

import java.util.Comparator;
import java.util.Date;

import controller.Comic;

public class PublicationDateSorter implements Comparator<Comic>{

    @Override
    public int compare(Comic comic1, Comic comic2) {
        Date date1 = comic1.getDate();
        Date date2 = comic2.getDate();

        return date1.compareTo(date2);
    }
    

    // Any Two instances of PublicationDateSorter are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PublicationDateSorter) ? true : false;
    }
}
