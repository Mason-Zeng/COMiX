package model.hierarchy;

import java.math.BigDecimal;
import java.util.List;

import model.Comic;

interface ComicHolder {
	public BigDecimal getValue();
  public int getIssueCount();
  public List<Comic> getIssues();
}
