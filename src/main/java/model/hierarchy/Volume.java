package model.hierarchy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Comic;

public class Volume implements ComicHolder {
  private Series series;
  private Map<String, Comic> issues;
  private int vol_num;

  public Volume(int vol_num) {
    this.vol_num = vol_num;
    this.issues = new HashMap<>();
  }

  public Volume(int vol_num, Series series) {
    new Volume(vol_num);
    setSeries(series);
  }

  public void addIssue(Comic issue) {
    issues.put(issue.getTitle(), issue);
    issue.setVolume(this);
  }
  
  public void delIssue(Comic issue) {
    issues.get(issue.getTitle()).setVolume(null);
    issues.remove(issue.getTitle());
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

  public Comic getIssue(String name) {
    return issues.get(name);
}

  public boolean issueExists(String name) {
    return issues.containsKey(name);
}

  @Override
  public BigDecimal getValue() {
    return issues.values().stream()
      .map(comic -> comic.getValue())
      .reduce(new BigDecimal(0), BigDecimal::add);
  }
  
  @Override
  public int getIssueCount() {
    return issues.size();
  }
  
  @Override
  public List<Comic> getIssues() {
    return new ArrayList<>(issues.values());
  }

  @Override
  public void delSelf() {
    series.delVolume(this);
    series = null;
  }
  
}
