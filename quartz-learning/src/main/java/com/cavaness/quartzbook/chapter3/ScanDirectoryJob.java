/**
 * 
 */
package com.cavaness.quartzbook.chapter3;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class ScanDirectoryJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(ScanDirectoryJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();

        String jobName = jobDetail.getName();
        logger.info(jobName + " fired at " + new Date());

        JobDataMap dataMap = jobDetail.getJobDataMap();

        String dirName = dataMap.getString("SCAN_DIR");

        if (dirName == null) {
            throw new JobExecutionException("directory not configured");
        }

        File dir = new File(dirName);

        if (!dir.exists()) {
            throw new JobExecutionException("Invalid dir " + dirName);

        }

        FileFilter filter = new FileExtensionFileFilter(".xml");

        File[] files = dir.listFiles(filter);

        if (files == null || files.length <= 0) {
            logger.info("no xml files foud in " + dir);
            return;
        }

        int size = files.length;
        for (int i = 0; i < size; i++) {
            File file = files[i];

            File aFile = file.getAbsoluteFile();
            long fileSize = file.length();
            String msg = aFile + "-size : " + fileSize;
            logger.info(msg);
        }
    }
}
