/**
 * 
 */
package com.zz.hash.shard.support;

import java.util.List;

import com.zz.hash.shard.FileWrapper;
import com.zz.hash.shard.Sharded;

/**
 * @author sunff
 *
 */
public class DefaultSharded extends Sharded<FileWrapper, DefaultShardInfo> {

    /**
     * @param shards
     */
    public DefaultSharded(List<DefaultShardInfo> shards) {
        super(shards);
    }

}
