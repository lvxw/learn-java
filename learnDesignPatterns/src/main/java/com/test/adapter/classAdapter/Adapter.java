package com.test.adapter.classAdapter;

/**
 * adapter 适配器角色,将源接口转换成目标接口
 */
public class Adapter extends UsbPlug implements Ps2Plug {
    @Override
    public void isPs2Plug() {
       isUsbPlug();
    }
}
