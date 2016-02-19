/**
 * 
 */
package com.cavaness.quartzbook.chapter3;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class Listing_3_6 {
    private static Logger logger = LoggerFactory.getLogger(Listing_3_6.class);

    public static void main(String args[]) {

        Listing_3_6 example = new Listing_3_6();
        try {
            Scheduler scheduler = example.createScheduler();

            scheduler.start();
            logger.info("scheduler started at " + new Date());

            example.scheduleJob(scheduler, "ScanDirectory1", ScanDirectoryJob.class, "d:/log/input1", 10);

            example.scheduleJob(scheduler, "ScanDirectory2", ScanDirectoryJob.class, "d:/log/input2", 15);

        } catch (SchedulerException ex) {
            logger.error(ex.toString());
        }
    }

    /**
     * @return
     */
    private Scheduler createScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }

    /**
     * @param scheduler
     */
    private void scheduleJob(Scheduler scheduler, String jobName, Class jobClass, String scanDir, int scanInterval)
            throws SchedulerException {

        JobDetail jobDeatail = new JobDetail(jobName, Scheduler.DEFAULT_GROUP, jobClass);
        jobDeatail.getJobDataMap().put("SCAN_DIR", scanDir);
        Trigger trigger = TriggerUtils.makeSecondlyTrigger(scanInterval);
        trigger.setName(jobName + "-Trigger");
        trigger.setStartTime(new Date());

        scheduler.scheduleJob(jobDeatail, trigger);

    }

}
