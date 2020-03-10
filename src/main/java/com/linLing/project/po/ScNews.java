package com.linLing.project.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sc_news", schema = "linling", catalog = "")
public class ScNews {
    private int id;
    private String newsTitle;
    private String newsContent;
    private String newsRemark;
    private String newsState;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "news_title", nullable = true, length = 255)
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Basic
    @Column(name = "news_content", nullable = true, length = 255)
    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @Basic
    @Column(name = "news_remark", nullable = true, length = 255)
    public String getNewsRemark() {
        return newsRemark;
    }

    public void setNewsRemark(String newsRemark) {
        this.newsRemark = newsRemark;
    }

    @Basic
    @Column(name = "news_state", nullable = true, length = 1)
    public String getNewsState() {
        return newsState;
    }

    public void setNewsState(String newsState) {
        this.newsState = newsState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScNews scNews = (ScNews) o;
        return id == scNews.id &&
                Objects.equals(newsTitle, scNews.newsTitle) &&
                Objects.equals(newsContent, scNews.newsContent) &&
                Objects.equals(newsRemark, scNews.newsRemark) &&
                Objects.equals(newsState, scNews.newsState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, newsTitle, newsContent, newsRemark, newsState);
    }
}

