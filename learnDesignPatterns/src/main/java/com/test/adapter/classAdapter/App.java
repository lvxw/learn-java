package com.test.adapter.classAdapter;

/**
 * client 测试类
 * 通过适配器，将ps2插头转换成usb插头
 */
public class App {
    public static void main(String[] args) {
        Ps2Plug adapter = new Adapter();
        adapter.isPs2Plug();
    }
}
