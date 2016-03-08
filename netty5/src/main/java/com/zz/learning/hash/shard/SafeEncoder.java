/**
 * 
 */
package com.zz.learning.hash.shard;

import java.io.UnsupportedEncodingException;

/**
 * @author sunff
 *
 */
public class SafeEncoder {
    public static byte[] encode(final String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
