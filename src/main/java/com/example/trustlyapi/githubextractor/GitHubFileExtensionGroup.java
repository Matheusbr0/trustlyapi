package com.example.trustlyapi.githubextractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teteu on 17/10/2020.

 try {
    GitHubFileExtractor gitHubFileExtractor = new GitHubFileExtractor("https://github.com/Matheusbr0/testPublic");
    gitHubFileExtractor.getReportByFileExtension();
 } catch (Exception ex){
    System.out.println(ex.getMessage());
 }

 */
public class GitHubFileExtensionGroup {
    private String extension;
    private List<GitHubFile> gitHubFileList;

    public GitHubFileExtensionGroup(String extension){
        this.extension = extension;
        this.gitHubFileList = new ArrayList<>();
    }

    public void addGitHubFile(GitHubFile gitHubFile){
        this.gitHubFileList.add(gitHubFile);
    }

    public int getTotalLines(){
        int total = 0;
        for (GitHubFile file:this.gitHubFileList){
            total += file.getLines();
        }
        return total;
    }

    public int getTotalBytes(){
        int total = 0;
        for (GitHubFile file:this.gitHubFileList){
            total += file.getSizeInBytes();
        }
        return total;
    }

    public String getExtension() {
        return extension;
    }

    public int getQuantityOfFiles(){
        return this.gitHubFileList.size();
    }
}
