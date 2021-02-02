package test;

import blxt.qjava.autovalue.QJavaApplication;
import blxt.qjava.autovalue.autoload.AutoLoadBase;
import blxt.qjava.autovalue.autoload.AutoUdpClient;
import blxt.qjava.autovalue.autoload.AutoUdpServer;
import blxt.qjava.autovalue.inter.ComponentScan;
import blxt.qjava.autovalue.inter.ConfigurationScan;
import blxt.qjava.autovalue.inter.EnFtpServer;
import blxt.qjava.autovalue.inter.EnHttpServer;
import blxt.qjava.autovalue.util.ObjectPool;
import blxt.qjava.qftp.MFtpServerFactory;
import blxt.qjava.quartz.QuartzManager;
import com.google.common.reflect.ClassPath;
import test.model.taks.TimeTask;
import test.util.AutowireEntry;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * @Author: Zhang.Jialei
 * @Date: 2020/12/5 22:41
 */
//@ConfigurationScan("src/main/test")
//@ComponentScan("src/main/test")
@EnFtpServer(port = 8080)
@ComponentScan()
@ConfigurationScan()
@EnHttpServer()
public class AutowireTest {

    public static void main(String[] args) throws Exception {


        // 在android中,可以使用指定的配置文件 ,使用文件流, 便于适配不同文件系统
        //FileInputStream inputStream = new FileInputStream(new File("E:\\Documents\\workspace\\java\\Stpringcloud\\" +
        //              "JavaQuick\\AutoValue\\src\\main\\resources/application2.properties"));
        //AutoValue.setPropertiesFile(inputStream), "utf-8");

      //    AutoUdpClient.load();
      //  AutoUdpServer.load();

     //   scanAutoLoad("");

        //   scanAutoLoad(AutowireTest.class.getClassLoader(), "blxt.qjava.autovalue.autoload");
        String JOB_NAME = "动态任务调度";
        String TRIGGER_NAME = "动态任务触发器";
        String JOB_GROUP_NAME = "XLXXCC_JOB_GROUP";
        String TRIGGER_GROUP_NAME = "XLXXCC_JOB_GROUP";
      //  QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, TimeTask.class, "0/1 * * * * ?");

        QJavaApplication.run(AutowireTest.class);
//
//        // 测试效果
        AutowireEntry autowireEntry = (AutowireEntry)ObjectPool.getObject(AutowireEntry.class);

       // MFtpServerFactory mFtpServerFactory = new MFtpServerFactory();

//
//        System.out.println(autowireEntry.toString());
    }


    /***
     * 扫描AutoLoad
     * @param packageName  包名
     * @return
     */
    public static Set<String> scanAutoLoad(String packageName){
        Set<String> classNames = new HashSet<String>();

        classNames = scan(packageName);
        if (classNames != null) {

            for (String className : classNames) {
                System.out.println(className);
            }
        }

        return classNames;
    }

    public static void scanAutoLoad(ClassLoader classloader, String packageName){

        ClassPath classpath = null;
        try {
            classpath = ClassPath.from(classloader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(packageName)) {
            System.out.println(classInfo.getName());
        }
    }

    /**
     * 获取指定包下以及子包中所有的类
     *
     * @param packageName 包名
     * @return 所有的完整类名
     */
    public static Set<String> scan(String packageName) {
        if(packageName == null){
            throw new RuntimeException("The path can not be null.");
        }
        Set<String> classNames = new HashSet<String>();
        String packagePath = packageName.replace(".", "/");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> urls = loader.getResources(packagePath);
            while(urls.hasMoreElements()){
                URL url= urls.nextElement();
                if("file".equals(url.getProtocol())){
                    classNames = scanFromDir(url.getPath(), packageName);
                }
                if("jar".equals(url.getProtocol())){
                    JarURLConnection connection = (JarURLConnection)url.openConnection();
                    classNames = scanFromJar(connection.getJarFile());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Resolve path error.", e);
        }

        return classNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath 文件目录
     * @param packageName 包名
     */
    private static Set<String>  scanFromDir(String filePath, String packageName) throws UnsupportedEncodingException{

        Set<String> classNames = new HashSet<String>();
        filePath = URLDecoder.decode(filePath, "utf-8");
        packageName = URLDecoder.decode(packageName, "utf-8");
        File[] files = new File(filePath).listFiles();
        packageName = packageName + ".";
        for (File childFile : files) {
            if (childFile.isDirectory()) {
                scanFromDir(childFile.getPath(), packageName + childFile.getName());
            } else {
                String fileName = childFile.getName();
                if (fileName.endsWith(".class")) {
                    if(packageName.charAt(0) == '.'){
                        packageName = packageName.substring(1, packageName.length());
                    }
                    String className = packageName + fileName.replace(".class", "");
                    classNames.add(className);
                }
            }
        }
        return classNames;
    }

    /**
     * 扫描jar文件
     * @param jarFile
     */
    private static Set<String> scanFromJar(JarFile jarFile) {
        Set<String> classNames = new HashSet<String>();
        Enumeration<JarEntry> files = jarFile.entries();
        while (files.hasMoreElements()) {
            JarEntry entry = files.nextElement();
            if (entry.getName().endsWith(".class")){
                String className = entry.getName().replace("/", ".").replace(".class", "");
                classNames.add(className);
            }
        }
        return classNames;
    }

}
