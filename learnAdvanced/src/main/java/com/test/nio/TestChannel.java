package com.test.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestChannel {

    private static void testFileChannel() throws Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\project_sync_repository\\learn-java\\.gitignore", "r");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while ((channel.read(buffer))!=-1){
            buffer.flip();
            while(buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }
            buffer.clear();
        }
    }


    private static void testFileChannel2() throws Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("2.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put((byte) 97);
        buffer.put((byte) 98);
        buffer.put((byte) 99);
        buffer.flip();


        channel.write(buffer);
    }

    private static void channelDataCopy() throws Exception{
        FileChannel srcChannel = new RandomAccessFile("E:\\project_sync_repository\\learn-java\\.gitignore", "rw").getChannel();
        FileChannel disChannel = new RandomAccessFile("2.txt", "rw").getChannel();

        disChannel.transferFrom(srcChannel,0,srcChannel.size());
    }

    private static void copyFileByChannel() throws Exception{
        FileChannel channel = new FileInputStream("2.txt").getChannel();
        FileChannel channel2 = new FileOutputStream("3.txt").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while((channel.read(buffer))!=-1){
                buffer.flip();
                channel2.write(buffer);
                buffer.clear();
            }

        channel2.close();
        channel.close();

    }
    private static void getChannel() throws Exception{
        FileChannel channel1 = FileChannel.open(Paths.get("E:\\project_sync_repository\\learn-java\\3.txt"), StandardOpenOption.READ);
        FileChannel channel2 = FileChannel.open(Paths.get("E:\\project_sync_repository\\learn-java\\4.txt"), StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);

//        SeekableByteChannel seekableByteChannel = Files.newByteChannel(Paths.get("2.txt"), StandardOpenOption.CREATE_NEW);

        MappedByteBuffer buffer1 = channel1.map(FileChannel.MapMode.READ_ONLY, 0, channel1.size());
        MappedByteBuffer buffer2 = channel2.map(FileChannel.MapMode.READ_WRITE, 0, channel1.size());

        byte[] bys = new byte[buffer1.limit()];
        buffer1.get(bys);
        buffer2.put(bys);

//        buffer2.flip();
//        System.out.println(buffer2.get());

        channel1.close();
        channel2.close();

    }


    private static void scatterReader() throws Exception{
        FileChannel channel = new RandomAccessFile("3.txt", "rw").getChannel();

        ByteBuffer buf1 = ByteBuffer.allocate(10);
        ByteBuffer buf2 = ByteBuffer.allocate(20);

        ByteBuffer[] bufArr = {buf1, buf2};

        channel.read(bufArr);
    }

    private static void testCharSet() throws Exception{
        Charset gbk = Charset.forName("GBK");
        CharsetEncoder encoder = gbk.newEncoder();

        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("你好世界");
        ByteBuffer encode = encoder.encode(buffer);

    }



    public static void main(String[] args) throws Exception {
//        testFileChannel2();
//        channelDataCopy();
//        copyFileByChannel();
//        getChannel();
        scatterReader();
    }
}
