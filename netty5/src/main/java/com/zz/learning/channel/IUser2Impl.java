/**
 * 
 */
package com.zz.learning.channel;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service
public class IUser2Impl  implements IUser{


    @Override
    public String getUserName(String userName) {

        return userName;
    }

}
