package com.mt.service;

import com.mt.po.DeviceConn;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Map;

public interface HandlerSelectionKey
{
    void handler(Map<String , DeviceConn> deviceConnMap , SelectionKey key, Selector selector) throws IOException;
}
