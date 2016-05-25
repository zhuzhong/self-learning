/**
 * 
 */
package com.j1.client.web;

import org.audit4j.core.annotation.Audit;

/**
 * @author Administrator
 *
 */
public interface ConfigService {

    @Audit
    public String hello(String name);
}
