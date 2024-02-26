package controller.sort;

import java.util.Comparator;

import model.Comic;

public class DefaultSorter implements Comparator<Comic> {

    @Override
    public int compare(Comic comic1, Comic comic2) {
        String title1 = comic1.getSeriesTitle();
        String title2 = comic2.getSeriesTitle();

        int seriesCompare = title1.compareTo(title2);
        int volumeCompare = comic1.getVolumeNumber() - comic2.getVolumeNumber();
        int issueCompare = comic1.getIssueNumber() -  comic2.getIssueNumber();
        
        if (seriesCompare == 0) {
            if (volumeCompare == 0) {
                return issueCompare;
            }
            return volumeCompare;
        }
        return seriesCompare;
    }

    // Any two instances of Default Sorter are considered equal
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof DefaultSorter) ? true : false;
    }
    
}
