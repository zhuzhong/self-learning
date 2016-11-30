package com.zz.learning.channel;
public interface FilterChain<T> {

	void go(T target) throws Exception;

}