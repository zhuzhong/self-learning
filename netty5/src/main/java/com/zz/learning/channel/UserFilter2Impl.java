package com.zz.learning.channel;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import c.z.zcommon.channel.Filter;
import c.z.zcommon.channel.FilterChain;

/**
 * @author Administrator
 *
 */
@Service
public class UserFilter2Impl implements Filter<User> ,Ordered {

   
    @Override
    public void filtrate(User target, FilterChain<User> chain) throws Exception {
       System.out.println(target.toString());
       System.out.println("filter2 处理user,order is "+getOrder());
       chain.go(target);
        
    }

    /* (non-Javadoc)
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 90;
    }
}
