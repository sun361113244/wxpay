package com.mt.po;

import java.nio.channels.SocketChannel;

/**
 * Created by 11 on 2019/3/3.
 */
public class DeviceConn {
    private SocketChannel socketChannel;
    private DeviceConnPo deviceConnPo;

    @Override
    public String toString() {
        return "DeviceConn{" +
                "socketChannel=" + socketChannel +
                ", deviceConnPo=" + deviceConnPo +
                '}';
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public DeviceConnPo getDeviceConnPo() {
        return deviceConnPo;
    }

    public void setDeviceConnPo(DeviceConnPo deviceConnPo) {
        this.deviceConnPo = deviceConnPo;
    }
}
