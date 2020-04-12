import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClassLoaderTest {
    public static URL myUrl;

    static {
        try {
            myUrl = new URL("http://www.google.es");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static class TestClassLoader1 extends ClassLoader {
        public TestClassLoader1() {
            //super(null);
        }

        @Override
        public Class findClass(String name) {
            byte[] b = loadClassFromFile(name);
            return defineClass(name, b, 0, b.length);
        }

        private byte[] loadClassFromFile(String fileName) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                    fileName.replace('.', File.separatorChar) + ".class");
            byte[] buffer;
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = 0;
            try {
                while ((nextValue = inputStream.read()) != -1) {
                    byteStream.write(nextValue);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer = byteStream.toByteArray();
            return buffer;
        }
    }


    @Test
    public void customClassLoader() throws ClassNotFoundException {
        Class<?> c1 = new TestClassLoader1().loadClass("ClassLoaderTest");
        Class<?> c2 = new TestClassLoader1().loadClass("ClassLoaderTest");
        System.out.println("Classloader of this class:"
                + ClassLoaderTest.class.getClassLoader());
        System.out.println("Classloader of ArrayList:"
                + ArrayList.class.getClassLoader());
        //System.out.println(c1.getClassLoader());
        //System.out.println(c2.getClassLoader());
        //assertEquals(c1, c2);
    }

    @Test
    public void staticFields() throws ClassNotFoundException, NoSuchFieldException {
        Class<?> c1 = new TestClassLoader1().loadClass("ClassLoaderTest");
        Class<?> c2 = new TestClassLoader1().loadClass("ClassLoaderTest");
        assertEquals( c1.getField("myUrl"), c2.getField("myUrl"));
    }
}
