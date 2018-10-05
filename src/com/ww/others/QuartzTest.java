
package com.ww.others;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Quartz学习
 */
public class QuartzTest {

  public void run() throws Exception {
    Logger log = LoggerFactory.getLogger(QuartzTest.class);

    log.info("------- Initializing ----------------------");

    // 创建Scheduler工厂
    SchedulerFactory sf = new StdSchedulerFactory();
    //从工厂中获取调度器
    Scheduler sched = sf.getScheduler();

    log.info("------- Initialization Complete -----------");

    // computer a time that is on the next round minute

    log.info("------- Scheduling Job  -------------------");

    // define the job and tie it to our HelloJob class
    //创建JobDeatil
    JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

    //时间
    Date runTime = evenMinuteDate(new Date());

    // Trigger the job to run on the next round minute
    //触发器
    //Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
    Trigger trigger = newTrigger().withIdentity("trigger3", "group1").startAt(runTime)
            .withSchedule(simpleSchedule().withIntervalInSeconds(5).withRepeatCount(3)).build();
    // Tell quartz to schedule the job using our trigger
    //注册任务和触发条件
    sched.scheduleJob(job, trigger);
    log.info(job.getKey() + " will run at: " + runTime);

    // Start up the scheduler (nothing can actually run until the
    // scheduler has been started)
    //启动
    sched.start();

    log.info("------- Started Scheduler -----------------");

    // wait long enough so that the scheduler as an opportunity to
    // run the job!
    log.info("------- Waiting 65 seconds... -------------");
    try {
      // wait 65 seconds to show job
      //5秒后停止
      Thread.sleep(65L * 1000L);
      // executing...
    } catch (Exception e) {
      //
    }

    // shut down the scheduler
    log.info("------- Shutting Down ---------------------");
    sched.shutdown(true);
    log.info("------- Shutdown Complete -----------------");
  }

  public static void main(String[] args) throws Exception {

    QuartzTest example = new QuartzTest();
    example.run();

  }

}
