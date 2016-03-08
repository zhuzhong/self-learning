/**
 * 
 */
package com.zz.hash.shard;

/**
 * @author sunff
 *
 */
public interface Hashing {

    public long hash(String key);

    public long hash(byte[] key);

}
