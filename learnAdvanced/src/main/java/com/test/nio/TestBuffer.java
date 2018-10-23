package com.test.nio;

import java.nio.ByteBuffer;

public class TestBuffer {

    private static  void testBuf() throws Exception{
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world".getBytes());

        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());

        byte[] bytes  = new byte[byteBuffer.limit()];
        byteBuffer.mark();
        byteBuffer.put("hello world".getBytes());
        byteBuffer.reset();


        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
    }

    private static void testDirectBuffer() throws Exception{
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.put("hello world".getBytes());
        buffer.flip();
        byte[] bys = new byte[buffer.limit()];
        buffer.get(bys);
        System.out.println(new String(bys));

    }

    public static void main(String[] args) throws Exception {

//        testBuf();
        testDirectBuffer();
    }
}
