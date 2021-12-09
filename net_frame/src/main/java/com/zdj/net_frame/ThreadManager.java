package com.zdj.net_frame;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *     author : dejinzhang
 *     time : 2021/03/09
 *     desc : 整体的运作机制
 * </pre>
 */
public class ThreadManager {
    /**
     * 单例
     */
    private static ThreadManager instance;
    private ThreadManager(){
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), (r, executor) -> addTask(r));
        threadPoolExecutor.execute(runnable);
    }
    public static ThreadManager getInstance() {
        if (instance == null) {
            synchronized(ThreadManager.class) {
                if (instance == null) {
                    instance = new ThreadManager();
                }
            }
        }
        return instance;
    }

    /**
     * 请求队列
     * LinkedBlockingQueue是带阻塞功能的：
     * 即往队列中放东西，大家去队列取东西的时候，队列中取不到数据，那么暂时这个线程就会被挂起来。
     */
    private LinkedBlockingQueue<Runnable> mQueue = new LinkedBlockingQueue<>();

    /**
     * 处理中心（线程池）
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 添加任务
     * @param runnable
     */
    public void addTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        mQueue.add(runnable);
    }

    /**
     * 核心线程
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = mQueue.take();
                    threadPoolExecutor.execute(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
