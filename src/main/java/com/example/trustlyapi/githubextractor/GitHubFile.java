package com.example.trustlyapi.githubextractor;

import com.example.trustlyapi.githubextractor.html.Html;
import com.example.trustlyapi.githubextractor.html.HtmlUtil;
import com.example.trustlyapi.githubextractor.html.Tag;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;

/**
 * Created by teteu on 17/10/2020.
 */
public class GitHubFile {
    //<div class="text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0">      65 lines (65 sloc)      <span class="file-info-divider"></span>    3.81 KB  </div>
    private static final String LINE_SIZE_DIV_TAG = " class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\"";
    private static final String LINE_SIZE_DIVIDER = "<span class=\"file-info-divider\"></span>";

    private static final String SIZE_BYTE = "Bytes";
    private static final String SIZE_KB = "KB";
    private static final String SIZE_MB = "MB";

    private String path;
    private Html html;

    private String fileName;
    private String extension;
    private int sizeInBytes;
    private int lines;//Quantity of lines of the file

    public GitHubFile(String path) throws Exception{
        this.path = path;
        //System.out.println("=============================================");
        this.html = new Html(this.path);
        String[] pathSplit = this.path.split("/");
        this.fileName = pathSplit[pathSplit.length - 1];
        this.extension = this.fileName.substring(this.fileName.indexOf("."));

        this.initFile();
        //System.out.println("File " + this.path + " loaded!");
    }

    private void initFile(){
        Tag lineSizeDivTag = HtmlUtil.getTagWithTextInInitTag(this.html.getContent(), "div", LINE_SIZE_DIV_TAG);
        String lineSizeInnerContent = lineSizeDivTag.getInnerContent().trim();
        if (lineSizeInnerContent.contains(LINE_SIZE_DIVIDER)){
            String[] linesBytes = lineSizeInnerContent.split(LINE_SIZE_DIVIDER);
            String lineNumber = linesBytes[0].substring(0, linesBytes[0].indexOf(" lines")).trim();
            this.lines = Integer.valueOf(lineNumber);
            this.sizeInBytes = this.getBytesFromString(linesBytes[1]);
        } else {
            this.sizeInBytes = this.getBytesFromString(lineSizeInnerContent);
        }
        //System.out.println("Extension: " + this.extension);
        //System.out.println("Size: " + this.sizeInBytes + " Bytes");
        //System.out.println("Lines: " + this.lines);
    }

    private int getBytesFromString(String sizeString){
        if (sizeString.contains(SIZE_BYTE)){
            return Integer.valueOf(sizeString.replace(SIZE_BYTE, "").trim());
        }else if (sizeString.contains(SIZE_KB)){
            return new BigDecimal(sizeString.replace(SIZE_KB, "").trim()).multiply(new BigDecimal("1000")).intValue();
        }else if (sizeString.contains(SIZE_MB)){
            return new BigDecimal(sizeString.replace(SIZE_MB, "").trim()).multiply(new BigDecimal("1000000")).intValue();
        } else {
            throw new NotImplementedException();
        }
    }

    public String getExtension() {
        return extension;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public int getLines() {
        return lines;
    }
}
