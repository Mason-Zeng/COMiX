package model.marking;

import java.math.BigDecimal;

import model.Comic;

public abstract class ComicDecorator implements Marking {
    protected Marking comic;

    public ComicDecorator(Marking comic) {
        this.comic = comic;
    }

    public BigDecimal getValue(){
        return comic.getValue();
    }

    public Comic getComic() {
        return (Comic)comic;
    }
}
