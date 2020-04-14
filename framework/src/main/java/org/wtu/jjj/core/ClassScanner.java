package org.wtu.jjj.core;

import javax.annotation.Resources;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @ClassName ClassScanner 类扫描器
 * @Description TODO
 * @Author 逝风无言
 * @Data 2020/4/6 10:24
 * @Version 1.0
 **/
public class ClassScanner {
    public static List<Class<?>> scanClasses(String packageName) throws IOException, ClassNotFoundException {
        List<Class<?>> classList = new ArrayList<>();
        String path = packageName.replace(".","/");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resurces = classLoader.getResources(path);
        while(resurces.hasMoreElements()){
            URL resource = resurces.nextElement();
            //查询以.jar包结尾
            if(resource.getProtocol().contains("jar")){
                JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                System.out.println("--jar:  "+jarFilePath);
                classList.addAll(getClassesFromJar(jarFilePath,path));
            }else{
                //todo
            }
        }
        return classList;
    }

    /**
     * 从jar文件中获取指定class
     * @param jarFilePath
     * @param path
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static List<Class<?>>getClassesFromJar(String jarFilePath,String path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()){
            JarEntry jarEntry = jarEntries.nextElement();
            String entryName = jarEntry.getName();// com/mooc/zbs/test/Test.class
            if(entryName.startsWith(path)&&entryName.endsWith(".class")){

                String classFullName = entryName.replace("/",".").substring(0,entryName.length()-6);
                classes.add(Class.forName(classFullName));
            }
        }
        return classes;
    }
}
