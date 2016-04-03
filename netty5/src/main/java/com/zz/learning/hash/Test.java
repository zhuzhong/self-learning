/**
 * 
 */
package com.zz.learning.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.zz.learning.hash.shard.FileWrapper;
import com.zz.learning.hash.shard.support.DefaultShardInfo;
import com.zz.learning.hash.shard.support.DefaultSharded;

/**
 * @author sunff
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		List<DefaultShardInfo> shards = new ArrayList<DefaultShardInfo>();
		for (int i = 0; i < 10; i++) {
			DefaultShardInfo d = new DefaultShardInfo();

			d.setName("file" + i);
			d.setPath("d:/data/test");
			shards.add(d);
		}

		DefaultSharded ds = new DefaultSharded(shards);
		testWrite(ds);

		testRead(ds);

		System.out.println("end...");

	}
//7955b237-4f25-4954-a17f-420b66131c21=7955b237-4f25-4954-a17f-420b66131c2195
	private static void testRead(DefaultSharded ds){
		 String keytest="7955b237-4f25-4954-a17f-420b66131c21";
		System.out.println(ds.getShard(keytest).getFileName());
		System.out.println(ds.getShard(keytest).getValue(keytest));
	}
	private static void testWrite(DefaultSharded ds) {
		for (int i = 0; i < 100; i++) {
			String key = UUID.randomUUID().toString();
			FileWrapper fw = ds.getShard(key);
			fw.write(key,key+i);
			System.out.println(fw.getFileName() + " " + key);
		}
	}
}
