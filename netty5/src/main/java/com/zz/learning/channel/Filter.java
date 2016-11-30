package com.zz.learning.channel;
public interface Filter<T> {

	void filtrate(T target, FilterChain<T> chain) throws Exception;

}
