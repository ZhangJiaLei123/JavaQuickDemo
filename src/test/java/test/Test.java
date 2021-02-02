package test;

import blxt.qjava.autovalue.autoload.AutoLoadBase;
import blxt.qjava.autovalue.reflect.PackageUtil;
import blxt.qjava.quartz.QuartzManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/12/19 16:53
 */
public class Test {

    public static String JOB_NAME = "动态任务调度";
    public static String TRIGGER_NAME = "动态任务触发器";
    public static String JOB_GROUP_NAME = "XLXXCC_JOB_GROUP";
    public static String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";

    public static void main(String[] args) throws Exception {
        try {

            System.out.println("【系统启动】开始(每1秒输出一次)...");
            QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, MJob.class, "0/1 * * * * ?");

            Thread.sleep(5000);
            System.out.println("【修改时间】开始(每5秒输出一次)...");
            QuartzManager.modifyJobTime(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, "0/5 * * * * ?");

            Thread.sleep(6000);
            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME);
            System.out.println("【移除定时】成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 扫描AutoLoad
     * @param packageName  包名
     * @return
     */
    public static List<AutoLoadBase> scanAutoLoad(String packageName){
        List<AutoLoadBase> autoLoads = null;

        List<String> classNames = PackageUtil.getClassName(packageName, true);
        if (classNames != null) {
            autoLoads = new ArrayList<>();
            for (String className : classNames) {
                // 过滤测试类
                System.out.println(className);
            }
        }

        return autoLoads;
    }


}
