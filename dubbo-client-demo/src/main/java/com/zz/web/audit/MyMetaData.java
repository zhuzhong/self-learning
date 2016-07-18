/**
 * 
 */
package com.zz.web.audit;

import org.audit4j.core.MetaData;

/**
 * @author Administrator
 *
 */
public class MyMetaData implements MetaData {

    public String getActor() {

        return "login-user";
    }

    public String getOrigin() {
        return "127.0.0.1";
    }

}
