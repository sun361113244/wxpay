package com.mt.listener;

import com.mt.service.HandlerSelectionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Component
public class DeviceListener implements InitializingBean
{
    private static Logger logger = LoggerFactory.getLogger(DeviceListener.class);

    private static List<SocketChannel> channels = Collections.synchronizedList(new ArrayList<SocketChannel>());

    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(
            r -> {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
    );

    @Resource
    private HandlerSelectionKey handlerSelectionKey;

    private void initListener()
    {
        singleThreadExecutor.execute(() -> {
            try
            {
                ServerSocketChannel server = ServerSocketChannel.open();
                server.configureBlocking(false);
                server.bind(new InetSocketAddress("0.0.0.0", 12345));

                Selector selector = Selector.open();
                server.register(selector, SelectionKey.OP_ACCEPT);
                while (true)
                {
                    //selector.select(); 是阻塞方法
                    int keys = selector.select();
                    if (keys > 0)
                    {
                        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                        while (it.hasNext())
                        {
                            SelectionKey key = it.next();
                            it.remove();
                            //处理 SelectionKey
                            handlerSelectionKey.handler(channels, key, selector);
                            Thread.sleep(100);
                        }
                    }
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        logger.info("initialize DeviceListener start");

        initListener();

        logger.info("initialize DeviceListener end");
    }
}
