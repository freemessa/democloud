package com.example.elasticsearch.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@Builder
public class Document {
    @Id
    private long documentId;
    private	String titles;
    private String contents;
    public long getDocumentId() {
        return documentId;
    }
    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }
    public String getTitles() {
        return titles;
    }
    public void setTitles(String titles) {
        this.titles = titles;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public Document(long documentId, String titles, String contents) {

        this.documentId = documentId;
        this.titles = titles;
        this.contents = contents;
    }
    public Document() {

    }

}
