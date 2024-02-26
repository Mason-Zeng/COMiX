package model.marking;

import java.math.BigDecimal;

import model.Comic;

public interface Marking{
    public BigDecimal getValue();
    public Comic getComic();
}
