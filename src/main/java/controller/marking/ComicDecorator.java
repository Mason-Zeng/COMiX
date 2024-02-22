package controller.marking;

import java.math.BigDecimal;

public abstract class ComicDecorator implements MarkImplement {
    protected MarkImplement comic;

    public ComicDecorator(MarkImplement comic) {
        this.comic = comic;
    }

    public BigDecimal getValue(){
        return comic.getValue();
    }
}
