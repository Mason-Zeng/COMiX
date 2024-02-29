package model.marking;

import java.math.BigDecimal;

import model.Comic;
/*
 * The component within the decorator pattern.
 * Defines the behavior for all types of marking.
 */
public interface Marking{
    public BigDecimal getValue();
    public Comic getComic();
}
