package com.example.android.newstask.models;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class News {
@SerializedName("status")
    private String status;
@SerializedName("totalResults")
    private int totalResult;
@SerializedName("articles")
    private List<Article> article;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }
}
