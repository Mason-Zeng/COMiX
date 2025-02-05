package model.hierarchy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.marking.Marking;

public class Volume implements ComicHolder {
  private Series series;
  private Map<String, Marking> issues;
  private String volString;

  public Volume(String volString) {
    this.volString = volString;
    this.issues = new HashMap<>();
  }

  public Volume(String volString, Series series) {
    this(volString);
    setSeries(series);
  }

  public void addIssue(Marking issue) {
    issues.put(issue.getTitle(), issue);
    issue.setVolume(this);
  }
  
  public void delIssue(Marking issue) {
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

  public String getVolumeNumber() {
    return volString;
  }

  public Marking getIssue(String name) {
    return issues.get(name);
}

  public boolean issueExists(String name) {
    return issues.containsKey(name);
}

  public void setVolNum(String num){
    this.volString = num;
  }

  @Override
  public BigDecimal getValue() {
    return issues.values().stream()
      .map(issue -> issue.getValue())
      .reduce(new BigDecimal(0), BigDecimal::add);
  }
  
  @Override
  public int getIssueCount() {
    return issues.size();
  }
  
  @Override
  public List<Marking> getIssues() {
    return new ArrayList<>(issues.values());
  }

  @Override
  public void delSelf() {
    series.delVolume(this);
    series = null;
  }

  @Override
  public List<ComicHolder> getChildren() {
    return new ArrayList<>();
  }

  public void setPublisher(String name){
    this.series.setPublisher(new Publisher(name));
  }
}
