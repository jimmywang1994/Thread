package com.ww.ThreadDemo;

public class TDownloader extends Thread {
    private String url;
    private String name;

    public TDownloader(String url, String name) {
        this.name = name;
        this.url = url;
    }

    @Override
    public void run() {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.download(url,name);
    }

    public static void main(String[] args) {
        TDownloader t1=new TDownloader("http://inews.gtimg.com/newsapp_bt/0/5449551533/641","kpl.jpg");
        TDownloader t2=new TDownloader("https://inews.gtimg.com/newsapp_bt/0/5453545405/1000","iwatch.jpg");
        TDownloader t3=new TDownloader("http://img1.gtimg.com/21/2146/214670/21467039_1200x1000_0.jpg","lol.jpg");
        t1.start();
        t2.start();
        t3.start();
    }
}
