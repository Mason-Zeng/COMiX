package controller;


import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ComicTest {
  @Test
  public void createComic() {
    Comic comic = new Comic("TestTitle", 1, "A test comic", new BigDecimal(10), LocalDate.of(2024, 2, 22));
  }
}
