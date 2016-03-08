/**
 * 
 */
package com.zz.learning.hash.shard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author sunff
 *
 */
public class FileWrapper {

    private String fileName;

    /**
     * @param string
     */
    public FileWrapper(String string) {
        this.fileName = string;
    }

    // 这个是追加式写入,将内容追加在文件的尾部
    public void write(String key, String value) {

        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);

            writer.write(key + "=" + value + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFileName() {
        return this.fileName;
    }

    public String getValue(String key) {
        // 根据key 从文件中读取
        File f = new File(fileName);
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p.getProperty(key);

    }
}
