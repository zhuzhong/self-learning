/**
 * 
 */
package com.cavaness.quartzbook.chapter3;

import java.util.Date;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class SimpleScheduler {

    private static Logger logger = LoggerFactory.getLogger(SimpleScheduler.class);

    public static void main(String args[]) {
        SimpleScheduler simple = new SimpleScheduler();
        simple.startScheduler();
    }

    public void startScheduler() {
        Scheduler scheduler = null;

        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            logger.info("scheduler started at " + new Date());
        } catch (SchedulerException ex) {
            logger.error(ex.toString());
        }
    }

    private void modifyScheduler(Scheduler scheduler) {
        try {
            if (!scheduler.isInStandbyMode()) {
                scheduler.standby();
            }
            scheduler.start();
        } catch (SchedulerException ex) {
            logger.error(ex.toString());
        }
    }
}
