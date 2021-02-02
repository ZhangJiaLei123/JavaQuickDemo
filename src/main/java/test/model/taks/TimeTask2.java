package test.model.taks;

import blxt.qjava.autovalue.inter.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

//@Scheduled(cron="0/1 * * * * ?")
public class TimeTask2 implements Job {

    int i = 0;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("TimeTask2: " + new Date() + "  " + i++);
    }
}
