package controller.hierarchy;

import java.math.BigDecimal;
import java.util.List;

import controller.Comic;

public interface ComicHolder {
	public BigDecimal getValue();
  public int getIssueCount();
  public List<Comic> getIssues();
  public void delSelf();
}
