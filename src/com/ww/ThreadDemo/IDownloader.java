package com.ww.ThreadDemo;

/**
 * 优先使用接口形式
 */
public class IDownloader implements Runnable {
    private String url;
    private String name;

    public IDownloader(String url, String name) {
        this.name = name;
        this.url = url;
    }

    @Override
    public void run() {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.download(url,name);
    }

    public static void main(String[] args) {
        IDownloader t1=new IDownloader("http://inews.gtimg.com/newsapp_bt/0/5449551533/641","kpl.jpg");
        IDownloader t2=new IDownloader("https://inews.gtimg.com/newsapp_bt/0/5453545405/1000","iwatch.jpg");
        IDownloader t3=new IDownloader("http://img1.gtimg.com/21/2146/214670/21467039_1200x1000_0.jpg","lol.jpg");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}
