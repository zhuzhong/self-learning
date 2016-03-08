/**
 * 
 */
package com.zz.learning.hash.shard;

/**
 * 分片信息
 * 
 * @author sunff
 *
 */
public abstract class ShardInfo<R> {

    private int weight;

    public ShardInfo() {
    }

    public ShardInfo(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    protected abstract R createResource();

    public abstract String getName();
}