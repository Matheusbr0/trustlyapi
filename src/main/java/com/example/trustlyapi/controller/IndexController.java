package com.example.trustlyapi.controller;

import com.example.trustlyapi.githubextractor.GitHubFileExtensionGroup;
import com.example.trustlyapi.githubextractor.GitHubFileExtractor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(@RequestParam(required = false) String gitHubUrl) {
        StringBuilder html = new StringBuilder();
        html.append("<html>")
            .append(getPageStyle())
            .append("<body>");
        if (gitHubUrl == null){
            html.append(this.getReportForm());
        }else{
            html.append(this.getReportHtml(gitHubUrl));
        }
        html.append("</body></html>");
        return html.toString();
    }

    private String getPageStyle(){
        StringBuilder html = new StringBuilder();
        html.append("<head><style>")
            .append("   body {")
            .append("       background-color: #EAEAEA;")
            .append("       font-family: Arial;")
            .append("       font-size: 14px;")
            .append("   }")
            .append("   form, div {")
            .append("       margin: 100px auto 0px auto;")
            .append("       width: 400px;")
            .append("   }")
            .append("   label {")
            .append("       font-weight: bold;")
            .append("   }")
            .append("   th {")
            .append("       font-weight: bold; background-color: #333333; color: #FFFFFF;")
            .append("   }")
            .append("   td {")
            .append("       background-color: #CCCCCC;")
            .append("   }")
            .append("</style></head>");
        return html.toString();
    }

    private String getReportForm(){
        StringBuilder html = new StringBuilder();
        html.append("<form action='' method='post'>")
            .append("   <label>Enter the GitHub Repository URL:</label></br>")
            .append("   <input name='gitHubUrl' type='text' style='width: 100%; margin: 2px 0px;' /></br>")
            .append("   <i style='font-size: 10px;'>Ex: https://github.com/Matheusbr0/testPublic</i></br></br>")
            .append("   <button type='submit' style='width: 100%;'>Extract data</button>")
            .append("</form>");
        return html.toString();
    }

    private String getReportHtml(String gitHubUrl){
        StringBuilder html = new StringBuilder();
        html.append("<div>")
            .append("<b>Repository:</b> " + gitHubUrl + "</br>")
            .append("<table style='width: 100%; border: 1px solid #333333;' cellpadding='5' cellspacing='0'>")
            .append("   <tr>")
            .append("       <th>Files</th>")
            .append("       <th>Extension</th>")
            .append("       <th>Lines</th>")
            .append("       <th>Bytes</th>")
            .append("   </tr>");
        try {
            GitHubFileExtractor gitHubFileExtractor = new GitHubFileExtractor(gitHubUrl);
            for (GitHubFileExtensionGroup group:gitHubFileExtractor.getGitHubFileExtensionGroupList()){
                html.append("<tr>")
                    .append("   <td>"+group.getQuantityOfFiles()+"</td>")
                    .append("   <td>"+group.getExtension()+"</td>")
                    .append("   <td>"+group.getTotalLines()+"</td>")
                    .append("   <td>"+group.getTotalBytes()+"</td>")
                    .append("</tr>");
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        html.append("</table></div>");
        return html.toString();
    }
}