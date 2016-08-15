package module5.task3.part2;

import module5.task3.part1.CustomClassLoader;

/**
 * Created by user on 06.08.2016.
 */
/*
2. Extend Custom Classloader:

Use custom class files from previous task. Extend classloader by adding ability to reload class at runtime.
Program should ask continuously for a class path. When path is entered to console - class should be reloaded.

a. Prepare several versions of Semaphor class - change message string for output.

b. Expected: after entering class path status messages and correct message are displayed in console.

 */
public class ExtendedCustomClassloader extends CustomClassLoader {
    CustomClassLoader loader;

    public ExtendedCustomClassloader(String classPath, ClassLoader parent) {
        super(classPath, parent);
        loader = new CustomClassLoader(classPath, parent);
    }

    public ExtendedCustomClassloader(String classPath) {
        super(classPath);
        loader = new CustomClassLoader(classPath);
    }

    public ExtendedCustomClassloader(ClassLoader classLoader) {
        super(classLoader);
        loader = new CustomClassLoader(classLoader);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            return super.loadClass(name);
        } else {
            loader = new CustomClassLoader(getClassPath(), getParent());
            c = loader.loadClass(name);
            return c;
        }
    }
}
