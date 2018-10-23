package com.test.io;


import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.URI;

public class App {

    private static void byteInputStream () throws IOException {
        InputStream is = App.class.getClassLoader().getResourceAsStream("logs/1.txt");
        System.out.println(is.getClass());
        int len;
        byte[] bys = new byte[1024];

        while((len=is.read(bys))!=-1){
            System.out.println(new String(bys,0, len));
        }
    }

    private static void testFile() throws Exception{
        URI uri = App.class.getClassLoader().getResource("logs/1.txt").toURI();
        File file = new File(uri);
        String name = file.getName();
        System.out.println(name);
        String path = file.getPath();
        System.out.println(path);
        String parent = file.getParent();
        System.out.println(parent);
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
        boolean exists = file.exists();
        System.out.println(exists);
    }

    private static void testRandomAccessFile() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:/project_sync_repository/learn-java/learnAdvanced/target/classes/logs/1.txt","rw+");
    }

    private static void testReader() throws Exception{
        Reader fr = new FileReader("E:/project_sync_repository/learn-java/learnAdvanced/target/classes/logs/1.txt");
        char[] chars = new char[1024];
        int len = fr.read(chars);
        System.out.println(new String(chars,0,len));
    }



    private static void testInputStream() throws Exception{
        byte[] bytes = "hello world".getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        int available = bais.available();
        System.out.println(available);
        boolean b = bais.markSupported();
        System.out.println(b);

    }

    public static void main(String[] args) throws Exception{
////        byteInputStream();
////        testFile();
////        testReader();
////        testInputStream();
//        FileReader fileReader = new FileReader("");
//        fileReader.read();
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String s = bufferedReader.readLine();
        PrintStream printStream = new PrintStream("");
        printStream.println(123);
    }
}
