package com.example.trustlyapi.githubextractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teteu on 17/10/2020.
 */
public class GitHubFileExtractor {
    private String rootPath;
    private GitHubDirectory rootDirectory;
    private List<GitHubFileExtensionGroup> gitHubFileExtensionGroupList;

    public GitHubFileExtractor (String rootPath) throws Exception{
        this.rootPath = rootPath;
        this.rootDirectory = new GitHubDirectory(this.rootPath);

        this.gitHubFileExtensionGroupList = new ArrayList<>();
        this.groupFilesByExtension(this.rootDirectory);
    }

    private void groupFilesByExtension(GitHubDirectory gitHubInitialDirectory){
        for (GitHubFile gitHubFile:gitHubInitialDirectory.getGitHubFileList()){
            this.addGitHubFileToExtensionGroup(gitHubFile);
        }

        for (GitHubDirectory gitHubDirectory:gitHubInitialDirectory.getGitHubDirectoryList()){
            this.groupFilesByExtension(gitHubDirectory);
        }
    }

    private void addGitHubFileToExtensionGroup(GitHubFile gitHubFile){
        boolean added = false;
        for (GitHubFileExtensionGroup group:this.gitHubFileExtensionGroupList){
            if (group.getExtension().equals(gitHubFile.getExtension())){
                group.addGitHubFile(gitHubFile);
                added = true;
                break;
            }
        }

        if (!added){
            GitHubFileExtensionGroup group = new GitHubFileExtensionGroup(gitHubFile.getExtension());
            this.gitHubFileExtensionGroupList.add(group);
            group.addGitHubFile(gitHubFile);
        }
    }

    public List<GitHubFileExtensionGroup> getGitHubFileExtensionGroupList() {
        return gitHubFileExtensionGroupList;
    }
}
