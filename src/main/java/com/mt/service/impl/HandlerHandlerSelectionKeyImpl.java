package com.mt.service.impl;

import com.mt.service.HandlerSelectionKey;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;

@Service
public class HandlerHandlerSelectionKeyImpl  implements HandlerSelectionKey
{
    @Override
    public void handler(List<SocketChannel> channels , SelectionKey key, Selector selector) throws IOException
    {
        int keyState = selectionKeyState(key);
        switch (keyState) {
            case SelectionKey.OP_ACCEPT:
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                accept(channels , serverSocketChannel, selector);
                break;
            case SelectionKey.OP_READ:
                SocketChannel readSocketChannel = (SocketChannel) key.channel();
                read(readSocketChannel, selector);
                break;
        }
    }
    /**
     * 获取 SelectionKey 是什么事件
     * @param key
     * @return
     */
    private int selectionKeyState(SelectionKey key) {
        if(key.isAcceptable()) {
            return SelectionKey.OP_ACCEPT;
        } else if(key.isReadable()) {
            return SelectionKey.OP_READ;
        }
        return -1;
    }

    /**
     * 接口客户端请求
     * @param serverSocketChannel
     * @param selector
     * @throws IOException
     */
    private void accept(List<SocketChannel> channels , ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        channels.add(socketChannel);
        //将 channel 注册到  Selector
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    /**
     * 读取客户端发送过来的信息
     * @param socketChannel
     * @param selector
     * @throws IOException
     */
    private void read( SocketChannel socketChannel, Selector selector) throws IOException {
        InetSocketAddress address = (InetSocketAddress) socketChannel.getRemoteAddress();
        String ip = address.getHostName();
        int port = address.getPort();

        ByteBuffer readBuffer = ByteBuffer.allocate(8192);
        int readBytes = socketChannel.read(readBuffer);
        String msg = "";//客户端发送来的消息
        if(readBytes > 0) {
            msg = new String(readBuffer.array(), 0, readBytes);
            System.out.println(String.format("%s:%d 发来信息:%s" , ip , port , msg));
        }
    }

    /**
     * 响应客户端请求
     * @param socketChannel
     * @param msg
     * @throws IOException
     */
    private void write(List<SocketChannel> channels , SocketChannel socketChannel, String msg) throws IOException {
        msg = "游客" + socketChannel.hashCode()+ "\r\n    " + msg;
        //响应消息
        byte[] responseByte = msg.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(responseByte.length);
        writeBuffer.put(responseByte);
        writeBuffer.flip();
        //响应客户端
        for(int i=0; i<channels.size(); i++) {
            if(!socketChannel.equals(channels.get(i))) {
                channels.get(i).write(writeBuffer);
            }
        }
    }
}
