package com.ww.ThreadDemo;

import java.util.concurrent.*;

/**
 * java.util.concurrent包中执行线程的方法
 */
public class CDownloader implements Callable<Boolean> {
    private String url;
    private String name;

    public CDownloader(String url, String name) {
        this.name = name;
        this.url = url;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CDownloader t1=new CDownloader("http://inews.gtimg.com/newsapp_bt/0/5449551533/641","kpl.jpg");
        CDownloader t2=new CDownloader("https://inews.gtimg.com/newsapp_bt/0/5453545405/1000","iwatch.jpg");
        CDownloader t3=new CDownloader("http://img1.gtimg.com/21/2146/214670/21467039_1200x1000_0.jpg","lol.jpg");
        //创建服务对象
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> r1=executorService.submit(t1);
        Future<Boolean> r2=executorService.submit(t2);
        Future<Boolean> r3=executorService.submit(t3);
        //获取结果
        boolean bl1=r1.get();
        boolean bl2=r2.get();
        boolean bl3=r3.get();
        executorService.shutdown();
    }

    @Override
    public Boolean call() throws Exception {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.download(url,name);
        System.out.println(name);
        return true;
    }
}
