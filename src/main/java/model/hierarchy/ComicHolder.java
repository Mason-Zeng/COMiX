package model.hierarchy;

import java.math.BigDecimal;
import java.util.List;

import model.Comic;


public interface ComicHolder {
  /**
   * Get the value of all issues
   * that belong to this holder.
   * 
   * @return BigDecimal totaling all issues
   */
	public BigDecimal getValue();

  /** 
   * Get the number of issues that
   * belong to this holder.
   * 
   * @return int number of issues
   */
  public int getIssueCount();
  
  /**
   * Get a list of all issues
   * that belong to this holder.
   * 
   * @return List of all Comics
   */
  public List<Comic> getIssues();

  /**
   * Remove this holder from the
   * parent and dereference it.
   * 
   * @return void
   */
  public void delSelf();

  /**
   * 
   * @return void
   */
  public List<ComicHolder> getChildren();
}
