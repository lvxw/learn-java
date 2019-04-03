package com.test.pkgscanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Class SimplePackageScanner is a package scanner which implements interface PackageScanner
 * and it offers functionally very simple.
 *
 * Created by SylvanasSun on 10/13/2017.
 */
public class SimplePackageScanner implements PackageScanner {

    protected String packageName;

    protected String packagePath;

    protected ClassLoader classLoader;


    public SimplePackageScanner() {
        this.classLoader = Thread.currentThread().getContextClassLoader();
    }

    @Override
    public List<Class<?>> scan(String packageName) {
        return this.scan(packageName, null);
    }

    @Override
    public List<Class<?>> scan(String packageName, ScannedClassHandler handler) {
        this.initPackageNameAndPath(packageName);
        System.out.printf("Start scanning package: {} ....\n", this.packageName);
        URL url = this.getResource(this.packagePath);
        if (url == null)
            return new ArrayList<>();
        return this.parseUrlThenScan(url, handler);
    }

    private void initPackageNameAndPath(String packageName) {
        this.packageName = packageName;
        this.packagePath = PathUtils.packageToPath(packageName);
    }

    protected URL getResource(String packagePath) {
        URL url = this.classLoader.getResource(packagePath);
        if (url != null)
            System.out.printf("Get resource: {} success!\n", packagePath);
        else
            System.out.printf("Get resource: {} failed,end of scan.\n", packagePath);
        return url;
    }

    protected List<Class<?>> parseUrlThenScan(URL url, ScannedClassHandler handler) {
        String urlPath = "";
        try {
            urlPath = PathUtils.getUrlMainPath(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.printf("Get url path failed.\n");
        }

        // decide file type
        ResourceType type = PathUtils.getResourceType(url);
        List<Class<?>> classList = new ArrayList<>();

        try {
            switch (type) {
                case FILE:
                    classList = this.getClassListFromFile(urlPath, this.packageName);
                    break;
                case JAR:
                    classList = this.getClassListFromJar(urlPath);
                    break;
                default:
                    System.out.printf("Unsupported file type.\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.printf("Get class list failed.\n");
        }

        this.invokeCallback(classList, handler);
        System.out.printf("End of scan <{}>.\n", urlPath);
        return classList;
    }

    protected List<Class<?>> getClassListFromFile(String path, String packageName) throws ClassNotFoundException {
        File file = new File(path);
        List<Class<?>> classList = new ArrayList<>();

        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                if (f.isDirectory()) {
                    List<Class<?>> list = getClassListFromFile(f.getAbsolutePath(),
                            PathUtils.concat(packageName, ".", f.getName()));
                    classList.addAll(list);
                } else if (PathUtils.isClassFile(f.getName())) {
                    // only add class file that not contain "$"
                    String className = PathUtils.trimSuffix(f.getName());
                    if (-1 != className.lastIndexOf("$"))
                        continue;

                    String finalClassName = PathUtils.concat(packageName, ".", className);
                classList.add(Class.forName(finalClassName));
            }
            }
        }

        return classList;
    }

    protected List<Class<?>> getClassListFromJar(String jarPath) throws IOException, ClassNotFoundException {
        System.out.printf("Start scanning jar: {}\n", jarPath);

        JarInputStream jarInputStream = new JarInputStream(new FileInputStream(jarPath));
        JarEntry jarEntry = jarInputStream.getNextJarEntry();
        List<Class<?>> classList = new ArrayList<>();

        while (jarEntry != null) {
            String name = jarEntry.getName();
            if (name.startsWith(this.packageName) && PathUtils.isClassFile(name))
                classList.add(Class.forName(name));
            jarEntry = jarInputStream.getNextJarEntry();
        }

        return classList;
    }

    protected void invokeCallback(List<Class<?>> classList, ScannedClassHandler handler) {
        if (classList != null && handler != null) {
            for (Class<?> clazz : classList) {
                handler.execute(clazz);
            }
        }
    }

    public static void main(String[] args) {
        PackageScanner packageScanner = new SimplePackageScanner();
        String packageName = "com.test.pkgscanner";
        List<Class<?>> classList = packageScanner.scan(packageName);
        System.out.println(classList);
    }
}