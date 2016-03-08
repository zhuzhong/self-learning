/**
 * 
 */
package com.zz.learning.hash.shard;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zz.learning.hash.shard.support.MurmurHash;

/**
 * @author sunff 分片
 */
public class Sharded<R, S extends ShardInfo<R>> {

    public static final int DEFAULT_WEIGHT = 1;
    private TreeMap<Long, S> nodes;
    private final Hashing algo;
    private final Map<ShardInfo<R>, R> resources = new LinkedHashMap<ShardInfo<R>, R>();

    /**
     * The default pattern used for extracting a key tag. The pattern must have
     * a group (between parenthesis), which delimits the tag to be hashed. A
     * null pattern avoids applying the regular expression for each lookup,
     * improving performance a little bit is key tags aren't being used.
     */
    private Pattern tagPattern = null;
    // the tag is anything between {}
    public static final Pattern DEFAULT_KEY_TAG_PATTERN = Pattern.compile("\\{(.+?)\\}");

    public Sharded(List<S> shards) {
        this(shards, new MurmurHash()); // MD5 is really not good as we works
        // with 64-bits not 128
    }

    public Sharded(List<S> shards, Hashing algo) {
        this.algo = algo;
        initialize(shards);
    }

    public Sharded(List<S> shards, Pattern tagPattern) {
        this(shards, new MurmurHash(), tagPattern); // MD5 is really not good
        // as we works with
        // 64-bits not 128
    }

    public Sharded(List<S> shards, Hashing algo, Pattern tagPattern) {
        this.algo = algo;
        this.tagPattern = tagPattern;
        initialize(shards);
    }

    private void initialize(List<S> shards) {
        nodes = new TreeMap<Long, S>();

        for (int i = 0; i != shards.size(); ++i) {
            final S shardInfo = shards.get(i);
            if (shardInfo.getName() == null) {
                for (int n = 0; n < 160 * shardInfo.getWeight(); n++) {
                    nodes.put(this.algo.hash("SHARD-" + i + "-NODE-" + n), shardInfo);
                }
            } else {
                for (int n = 0; n < 160 * shardInfo.getWeight(); n++) {
                    nodes.put(this.algo.hash(shardInfo.getName() + "*" + shardInfo.getWeight() + n), shardInfo);
                }
            }
            resources.put(shardInfo, shardInfo.createResource());
        }
    }

    public R getShard(byte[] key) {
        return resources.get(getShardInfo(key));
    }

    public R getShard(String key) {
        return resources.get(getShardInfo(key));
    }

    public S getShardInfo(byte[] key) {
        /*
         * tailMap public SortedMap<K,V> tailMap(K fromKey)从接口 NavigableMap
         * 复制的描述 返回此映射的部分视图，其键大于等于
         * fromKey。返回的映射受此映射支持，所以在返回映射中的更改将反映在映射中，反之亦然。返回的映射支持此映射支持的所有可选映射操作。
         * 如果试图在返回映射的范围之外插入键，则返回的映射将抛出 IllegalArgumentException。
         * 
         * 等效于 tailMap(fromKey, true)。
         */
        SortedMap<Long, S> tail = nodes.tailMap(algo.hash(key));
        if (tail.isEmpty()) {
            /*
             * public K firstKey()从接口 SortedMap 复制的描述 返回此映射中当前第一个（最低）键。
             */
            return nodes.get(nodes.firstKey());
        }
        return tail.get(tail.firstKey());
    }

    public S getShardInfo(String key) {
        return getShardInfo(SafeEncoder.encode(getKeyTag(key)));
    }

    /**
     * A key tag is a special pattern inside a key that, if preset, is the only
     * part of the key hashed in order to select the server for this key.
     * 
     * @see http://code.google.com/p/redis/wiki/FAQ#I
     *      'm_using_some_form_of_key_hashing_for_partitioning,_but_wh
     * @param key
     * @return The tag if it exists, or the original key
     */
    public String getKeyTag(String key) {
        if (tagPattern != null) {
            Matcher m = tagPattern.matcher(key);
            if (m.find())
                return m.group(1);
        }
        return key;
    }

    public Collection<S> getAllShardInfo() {
        return Collections.unmodifiableCollection(nodes.values());
    }

    public Collection<R> getAllShards() {
        return Collections.unmodifiableCollection(resources.values());
    }
}
