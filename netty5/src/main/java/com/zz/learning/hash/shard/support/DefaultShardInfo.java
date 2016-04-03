/**
 * 
 */
package com.zz.learning.hash.shard.support;

import java.io.File;

import com.zz.learning.hash.shard.FileWrapper;
import com.zz.learning.hash.shard.ShardInfo;

/**
 * @author sunff
 *
 */
public class DefaultShardInfo extends ShardInfo<FileWrapper> {

    public DefaultShardInfo(int weight) {
        super(weight);
    }

    public DefaultShardInfo() {
        this(1);
    }

    @Override
    protected FileWrapper createResource() {
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        FileWrapper fw = new FileWrapper(path + "/" + name);
        return fw;
    }

    @Override
    public String getName() {
        return name;
    }

    // 文档的名字
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    private String path;// 文档的路径

    public void setPath(String path) {
        this.path = path;
    }
}
