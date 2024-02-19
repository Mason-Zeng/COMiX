package controller;

import java.math.BigDecimal;
import java.util.List;

interface ComicHolder {
	public BigDecimal getValue();
  public int getIssueCount();
  public List<Comic> getIssues();
}
