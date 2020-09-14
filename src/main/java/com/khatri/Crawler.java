package com.khatri;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yash Khatri
 */

class Crawler {

    private static final int MAX_DEPTH = 5;
    //Queue of all Urls. It will be visited in FIFO order.
    private Queue<String> allUrls;
    //Set of all visited URLs
    private HashSet<String> visitedUrls;

    Crawler() {
        this.allUrls = new LinkedList<>();
        this.visitedUrls = new HashSet<>();
    }

    void visit(String root) {

        this.allUrls.add(root);
        this.visitedUrls.add(root);
        int depth = 1;

        while (!allUrls.isEmpty() && depth <= MAX_DEPTH) {
            String v = allUrls.remove();
            //Remove function returns the head of the queue and remove it from the queue.

            String urlData = readUrl(v);
            //regex to identify urls.
            String regex = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(urlData);

            while (matcher.find()) {
                String urlFound = matcher.group();
                if (!visitedUrls.contains(urlFound)) {
                    visitedUrls.add(urlFound);
                    System.out.println("Depth: " + depth + ": Url: " + urlFound);
                    allUrls.add(urlFound);
                }
            }
            depth += 1;
        }
    }

    //reading the web page in string format
    private String readUrl(String urlString) {
        StringBuilder urlData = new StringBuilder();
        try {
            URL url = new URL(urlString);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String input;
            while ((input = br.readLine()) != null) {
                urlData.append(input);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("Exception caught while trying to read : " + urlString);
        }

        return urlData.toString();
    }
}
