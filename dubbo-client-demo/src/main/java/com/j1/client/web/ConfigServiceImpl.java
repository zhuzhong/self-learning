/**
 * 
 */
package com.j1.client.web;

/**
 * @author Administrator
 *
 */
public class ConfigServiceImpl implements ConfigService {

    private String index;

    public void setIndex(String index) {
        this.index = index;
    }

    public String hello(String name) {
        return index + "---------------" + name;
    }

}
