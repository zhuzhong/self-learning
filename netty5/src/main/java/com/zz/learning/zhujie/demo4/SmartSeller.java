/**
 * 
 */
package com.zz.learning.zhujie.demo4;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class SmartSeller implements Seller {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.zz.learning.zhujie.demo4.Seller#sell(java.lang.Long)
	 */
	@Override
	public void sell(Long goodsId) {
		System.out.println("smart seller sell " + goodsId);

	}

}
