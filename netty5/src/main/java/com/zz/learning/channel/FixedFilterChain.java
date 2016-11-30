package com.zz.learning.channel;

import java.util.Iterator;

public class FixedFilterChain<T> implements FilterChain<T> {
    
    private final Iterator<Filter<T>> filters;

    public FixedFilterChain(Iterator<Filter<T>> filters) {
        super();
        this.filters = filters;
    }

    public FixedFilterChain(Iterable<Filter<T>> filters) {
        super();
        this.filters = filters.iterator();
    }

    public void go(T target) throws Exception {
        if (filters.hasNext())
            filters.next().filtrate(target, this);
    }

}