package model;


import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.hierarchy.Volume;

public class ComicTest {
  @Test
  public void createComic() {
    new Comic("TestTitle", "1", "A test comic", new BigDecimal(10), LocalDate.of(2024, 2, 22), new Volume("2"));
  }
}
