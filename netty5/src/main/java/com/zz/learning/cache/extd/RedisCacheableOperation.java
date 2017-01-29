/**
 * 
 */
package com.zz.learning.cache.extd;

import org.springframework.cache.interceptor.CacheOperation;

/**
 * @author sunff
 *
 */
public class RedisCacheableOperation  extends CacheOperation  {

	
	private String unless;


	public String getUnless() {
		return unless;
	}

	public void setUnless(String unless) {
		this.unless = unless;
	}
	
	
	private int time;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	@Override
	protected StringBuilder getOperationDescription() {
		StringBuilder sb = super.getOperationDescription();
		sb.append(" | unless='");
		sb.append(this.unless);
		sb.append("'");
		sb.append(" | time='");
		sb.append(this.time);
		sb.append("'");
		return sb;
	}
}
