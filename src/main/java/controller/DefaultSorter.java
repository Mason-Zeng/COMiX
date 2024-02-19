package controller;

import java.util.Comparator;

public class DefaultSorter implements Comparator<Comic> {

    @Override
    public int compare(Comic comic1, Comic comic2) {
        String title1 = comic1.getTitle();
        String title2 = comic2.getTitle();

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
    
}
