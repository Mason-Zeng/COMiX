package model.hierarchy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Comic;

public class Collection implements ComicHolder{
    private String user;
    private Map<String, Publisher> publishers;
    
    public Collection(String user) {
        this.user = user;
        this.publishers = new HashMap<>();
    }

    public String getUser() {
        return user;
    }

    public void addPublisher(Publisher publisher) {
        publishers.put(publisher.getName(), publisher);
    }

    public void delPublisher(Publisher publisher) {
        publishers.remove(publisher.getName());
    }

    @Override
    public BigDecimal getValue() {
        return publishers.values().stream()
            .map(pub -> pub.getValue())
            .reduce(new BigDecimal(0), BigDecimal::add);
    }

    @Override
    public int getIssueCount() {
        return publishers.values().stream()
            .mapToInt(Publisher::getIssueCount)
            .sum();
    }

    @Override
    public List<Comic> getIssues() {
        return publishers.values().stream()
            .map(Publisher::getIssues)
            .collect(ArrayList::new, List::addAll, List::addAll);
    }

    @Override
    public void delSelf() {
        throw new UnsupportedOperationException("Cannot Delete Collection");
    }
    
}
