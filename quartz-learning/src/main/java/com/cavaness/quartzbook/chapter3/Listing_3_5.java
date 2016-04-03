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
public class Listing_3_5 {

    private static Logger logger = LoggerFactory.getLogger(Listing_3_5.class);

    public static void main(String args[]) {

        Listing_3_5 example = new Listing_3_5();
        try {
            Scheduler scheduler = example.createScheduler();
            example.scheduleJob(scheduler);

            scheduler.start();
            logger.info("scheduler started at " + new Date());
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
    private void scheduleJob(Scheduler scheduler)  throws SchedulerException{
       
       
        JobDetail jobDeatail=new JobDetail("ScanDirectory",Scheduler.DEFAULT_GROUP,
                ScanDirectoryJob.class
                );
        jobDeatail.getJobDataMap().put("SCAN_DIR", "d:/logs/");
        Trigger trigger=TriggerUtils.makeSecondlyTrigger(10);
        trigger.setName("scanTrigger");
        trigger.setStartTime(new Date());
        
        scheduler.scheduleJob(jobDeatail, trigger);
                
    }

}
