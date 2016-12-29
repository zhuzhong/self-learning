/**
 * 
 */
package com.zz.learning.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class DemoServiceImpl implements DemoService{

    /* (non-Javadoc)
     * @see com.zz.learning.cache.DemoService#getStrByName(java.lang.String)
     */
    @Cacheable("default")
    @Override
    public String getStrByName(String name) {

       return getNameFromDb(name);
    }
    
    
    private String getNameFromDb(String name){
        System.out.println("from db....");
        return "db-"+name;
    }

}
