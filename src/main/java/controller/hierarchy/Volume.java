package controller.hierarchy;

import java.math.BigDecimal;
import java.util.List;

import controller.Comic;

public class Volume implements ComicHolder {
  private Series series;
  private List<Comic> issues;
  private int vol_num;

  public Volume(int vol_num, Series series) {
    this.vol_num = vol_num;
    this.series = series;
  }

  public Volume(int vol_num) {
    new Volume(vol_num, null);
  }

  public void addIssue(Comic issue) {
    issues.add(issue);
  }

  public void setSeries(Series series) {
    this.series = series;
  }

  public Publisher getPublisher() {
    return series.getPublisher();
  }

  public Series getSeries() {
    return series;
  }

  public int getVolumeNumber() {
    return vol_num;
  }

  public BigDecimal getValue() {
    return issues.stream()
      .map(comic -> comic.getValue())
      .reduce(new BigDecimal(0), BigDecimal::add);
  }
  
  public int getIssueCount() {
    return issues.size();
  }
  
  public List<Comic> getIssues() {
    return issues;
  }
  
}
