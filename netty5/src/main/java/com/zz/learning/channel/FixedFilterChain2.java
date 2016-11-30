/**
 * 
 */
package com.zz.learning.channel;

import java.util.Iterator;

/**
 * @author Administrator
 *
 */
public class FixedFilterChain2<T> implements FilterChain<T> {
    
    private final Iterator<Filter<T>> filters;

    public FixedFilterChain2(Iterator<Filter<T>> filters) {
        super();
        this.filters = filters;
    }

    public FixedFilterChain2(Iterable<Filter<T>> filters) {
        super();
        this.filters = filters.iterator();
    }

    public void go(T target) throws Exception {
        if (filters.hasNext())
            filters.next().filtrate(target, this);
    }

}
