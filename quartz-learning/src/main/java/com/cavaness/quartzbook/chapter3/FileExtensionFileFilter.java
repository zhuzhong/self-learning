/**
 * 
 */
package com.cavaness.quartzbook.chapter3;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Administrator
 *
 */
public class FileExtensionFileFilter implements FileFilter {

    private String extend;

    public FileExtensionFileFilter(String extend) {
        this.extend = extend;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.io.FileFilter#accept(java.io.File)
     */
    public boolean accept(File pathname) {
        String lowCaseFileName = pathname.getName().toLowerCase();
        return pathname.isFile() && (lowCaseFileName.indexOf(extend) > 0);
    }

}
