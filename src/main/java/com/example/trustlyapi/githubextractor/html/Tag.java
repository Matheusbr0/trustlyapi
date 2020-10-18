package com.example.trustlyapi.githubextractor.html;

/**
 * Created by teteu on 17/10/2020.
 */
public class Tag {
    private String name;
    private String content;

    public Tag(String content){
        this.content = content;
        this.name = this.content.substring(1, this.content.indexOf(" "));
    }

    public String getContent() {
        return content;
    }

    public String getTagAttributeValue(String attributeName){
        String attributeInitialPart = attributeName+"=\"";
        int indexOfAttribute = this.content.indexOf(attributeInitialPart);
        String attributeValue = this.content.substring(indexOfAttribute + attributeInitialPart.length());
        int indexOfAttributeEnd = attributeValue.indexOf("\"");
        return attributeValue.substring(0, indexOfAttributeEnd);
    }

    public String getInnerContent(){
        return this.content.substring(this.content.indexOf(">")+1, this.content.length() - ("</"+this.name+">").length());
    }
}
