package com.mt.service;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;

public interface HandlerSelectionKey
{
    void handler(List<SocketChannel> channels , SelectionKey key, Selector selector) throws IOException;
}
