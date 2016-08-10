package module5.task3.part1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by user on 06.08.2016.
 */
/*
1. Implement Custom Classloader:


 Custom classloader should be able to load compiled Semaphore.class file. Class path is passed via console on program start.
 Status progress/success/error should be output to console.
Semaphore object should be instantiated. Method  lever should be invoked. Use reflection. Google exmples on reflection API.
Expected to see "It works!" message on the console.
// soure code, compile it

package com.epam.mentoring.lessone;
public class Semaphore {
    public void lever() {
        System.out.println("It works!");
    }
}
 */
public class CustomClassLoader extends ClassLoader {
    protected String classPath;

    public CustomClassLoader(String classPath, ClassLoader parent) {
        super(parent);
        this.classPath = classPath;
    }

    public CustomClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }

    public CustomClassLoader() {
        super(CustomClassLoader.getSystemClassLoader());
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = classPath + File.separator + name.replace(".", File.separator) + ".class";
        Path path = Paths.get(fileName);
        System.out.println(path.toString());
        byte[] b = null;
        try {
            b = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
        if (null != b) {
            return defineClass(name, b, 0, b.length);
        }

        throw new ClassNotFoundException(name);
    }

}
