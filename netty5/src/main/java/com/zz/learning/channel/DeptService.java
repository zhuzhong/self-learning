/**
 * 
 */
package com.zz.learning.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class DeptService {

    
    private UserService userSevice;
    @Autowired
    public DeptService(UserService userSevice){
        this.userSevice=userSevice;
    }
    
    public void dealUser(User u){
        userSevice.dealUser(u);
    }
}
