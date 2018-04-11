package com.test.adapter.objectAdapter;

/**
 * adapter 适配器角色,将源接口转换成目标接口
 */
public class Adapter implements Ps2Plug {
    private UsbPlug usbPlug = new UsbPlug();

    @Override
    public void isPs2Plug() {
        usbPlug.isUsbPlug();
    }
}
