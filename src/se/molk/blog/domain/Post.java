package se.molk.blog.domain;

public class Post {
    private int id;
    private String title;
    private String body;
    private String date;
    private String author;
    private Category category;
    private boolean published;
    private int userId;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthor(String author) {
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

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAuthor() {
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

    public int getUserId() {
        return userId;
    }

    // public boolean userId() {
    //}


}
