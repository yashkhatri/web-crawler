package com.khatri;

/**
 * @author Yash Khatri
 */

public class WebCrawlerApp {

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        String root = "https://www.google.com";
        crawler.visit(root);
    }
}