package com.example.trustlyapi.githubextractor.html;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by teteu on 17/10/2020.
 */
public class Html {
    private String content;

    public Html(String url) throws Exception{
        this.content = this.getUrlHtmlContent(url);
    }

    private String getUrlHtmlContent(String url) throws Exception{
        URL urlObj = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(urlObj.openStream()));
        StringBuilder sb = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            //System.out.println(inputLine);
            sb.append(inputLine);
        }
        in.close();
        return sb.toString();
    }

    public String getContent() {
        return content;
    }
}
