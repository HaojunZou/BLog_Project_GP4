package se.molk.blog.domain;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String body;
    private String date;
    private User author;
    private Category category;
    private boolean published;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isPublished() {
        return published;
    }

}
