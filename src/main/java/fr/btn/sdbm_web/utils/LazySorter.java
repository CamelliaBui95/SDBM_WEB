package fr.btn.sdbm_web.utils;

import fr.btn.sdbm_web.metier.Article;
import org.primefaces.model.SortOrder;

import java.util.Comparator;

public class LazySorter implements Comparator<Article> {
    private String sortField;
    private SortOrder order;

    public LazySorter(String sortField, SortOrder order) {
        this.sortField = sortField;
        this.order = order;
    }

    @Override
    public int compare(Article a1, Article a2) {
        try {
            Object val1 = a1.getComparatorValue(sortField);
            Object val2 = a2.getComparatorValue(sortField);

            int value = ((Comparable) val1).compareTo(val2);

            return SortOrder.ASCENDING.equals(order) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
