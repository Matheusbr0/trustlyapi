package com.example.trustlyapi.githubextractor;

import com.example.trustlyapi.githubextractor.html.Html;
import com.example.trustlyapi.githubextractor.html.HtmlUtil;
import com.example.trustlyapi.githubextractor.html.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teteu on 17/10/2020.
 */
public class GitHubDirectory {
    public static final String GITHUB_URL                 = "https://github.com";
    public static final String DIV_FILES_TAG_CONTENT      = " role=\"grid\" aria-labelledby=\"files\"";
    public static final String DIV_FILES_ROWN_TAG_CONTENT = " role=\"row\" class=\"Box-row Box-row--focus-gray py-2 d-flex position-relative js-navigation-item \"";
    public static final String SVG_DIRECTORY_ARIA_LABEL   = " aria-label=\"Directory\"";
    public static final String SVG_FILE_ARIA_LABEL        = " aria-label=\"File\"";
    public static final String A_DIRECTORY_FILE_CONTENT   = " class=\"js-navigation-open link-gray-dark\"";

    private String path;
    private Html html;
    private Tag filesDivTag;
    private List<Tag> filesRownDivTags;

    private List<GitHubFile> gitHubFileList;
    private List<GitHubDirectory> gitHubDirectoryList;

    public GitHubDirectory(String path) {
        this.path = path;
        try {
            System.out.println("Loading Directory " + this.path);
            this.html = new Html(this.path);
            this.filesDivTag = HtmlUtil.getTagWithTextInInitTag(this.html.getContent(), "div", DIV_FILES_TAG_CONTENT);
            this.filesRownDivTags = HtmlUtil.getTagsWithTextInInitTag(
                    this.filesDivTag.getContent(), "div", DIV_FILES_ROWN_TAG_CONTENT
            );

            this.initFolders();
            this.initFiles();
        }catch (Exception ex){
            System.out.println("Error loading directory: " + this.path);
        }
        //System.out.println("Directory " + this.path + " loaded!");
    }

    private void initFolders() throws  Exception{
        String directoryPath;
        this.gitHubDirectoryList = new ArrayList<>();
        for (Tag filesRownTag:this.filesRownDivTags){
            if (this.rownTagIsDirectory(filesRownTag)){
                Tag fileATag = HtmlUtil.getTagWithTextInInitTag(filesRownTag.getContent(), "a", A_DIRECTORY_FILE_CONTENT);
                directoryPath = GITHUB_URL+fileATag.getTagAttributeValue("href");
                this.gitHubDirectoryList.add(new GitHubDirectory(directoryPath));
            }
        }
    }

    private void initFiles() throws Exception{
        this.gitHubFileList = new ArrayList<>();
        for (Tag filesRownTag:this.filesRownDivTags){
            if (this.rownTagIsFile(filesRownTag)){
                Tag fileATag = HtmlUtil.getTagWithTextInInitTag(filesRownTag.getContent(), "a", A_DIRECTORY_FILE_CONTENT);
                this.gitHubFileList.add(new GitHubFile(GITHUB_URL+fileATag.getTagAttributeValue("href")));
            }
        }
    }

    private boolean rownTagIsDirectory(Tag rownTag){
        Tag svgTag = HtmlUtil.getTagWithTextInInitTag(rownTag.getContent(), "svg", SVG_DIRECTORY_ARIA_LABEL);
        return svgTag != null;
    }

    private boolean rownTagIsFile(Tag rownTag){
        Tag svgTag = HtmlUtil.getTagWithTextInInitTag(rownTag.getContent(), "svg", SVG_FILE_ARIA_LABEL);
        return svgTag != null;
    }

    public List<GitHubDirectory> getGitHubDirectoryList(){
        return this.gitHubDirectoryList;
    }

    public List<GitHubFile> getGitHubFileList(){
        return this.gitHubFileList;
    }
}
