package com.example.trustlyapi.githubextractor.html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by teteu on 17/10/2020.
 */
public class HtmlUtil {

    private HtmlUtil(){}

    public static List<Tag> getTagsWithTextInInitTag(String html, String tagName, String initTagContet){
        List<Tag> tagList = new ArrayList<>();

        String htmlToCheck = html;
        Tag tag;
        while (htmlToCheck.length() > 0){
            tag = getTagWithTextInInitTag(htmlToCheck, tagName, initTagContet);
            if (tag == null){
                break;
            } else {
                tagList.add(tag);
                htmlToCheck = htmlToCheck.substring(htmlToCheck.indexOf(tag.getContent()) + tag.getContent().length());
            }
        }

        return tagList;
    }

    public static Tag getTagWithTextInInitTag(String html, String tagName, String initTagContet){
        int indexOfInitialTag = indexOfRegularExpression(html, "<"+tagName+initTagContet+"(.*?)>");

        if (indexOfInitialTag >= 0) {
            String htmlFromInitialTag = html.substring(indexOfInitialTag);

            int openTags = 1;
            int fromIndex = 0;
            int indexOfNextOpenTag = -1;
            int indexOfNextCloseTag = -1;

            while (openTags > 0) {
                indexOfNextOpenTag = htmlFromInitialTag.indexOf("<" + tagName, fromIndex + 1);
                indexOfNextCloseTag = htmlFromInitialTag.indexOf("</" + tagName, fromIndex + 1);
                if (indexOfNextOpenTag >= 0 && indexOfNextOpenTag < indexOfNextCloseTag) {
                    openTags++;
                    fromIndex = indexOfNextOpenTag;
                } else {
                    openTags--;
                    fromIndex = indexOfNextCloseTag;
                }
            }

            htmlFromInitialTag = htmlFromInitialTag.substring(0, fromIndex + ("</" + tagName + ">").length());
            return new Tag(htmlFromInitialTag);
        } else {
            return null;
        }
    }

    private static int indexOfRegularExpression(String html, String regularExpression){
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(html);
        if(matcher.find()){
            return matcher.start();
        }
        return -1;
    }
}
