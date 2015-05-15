package se.molk.blog.domain;

public class Category {
    private int cate_id;
    private String name;

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCate_id() {
        return cate_id;
    }

    public String getName() {
        return name;
    }
}
