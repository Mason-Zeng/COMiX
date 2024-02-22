package controller.sort;

import java.time.LocalDate;
import java.util.Comparator;

import controller.Comic;

public class PublicationDateSorter implements Comparator<Comic>{

    @Override
    public int compare(Comic comic1, Comic comic2) {
        LocalDate date1 = comic1.getDate();
        LocalDate date2 = comic2.getDate();

        return date1.compareTo(date2);
    }
    

    // Any Two instances of PublicationDateSorter are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PublicationDateSorter) ? true : false;
    }
}
