package se.molk.blog.domain;

public class Comment {

    private int comment_id;
    private int post_id;
    private String commentBody;
    private String commentDate;

    public int getComment_id() {
        return comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public String getCommentDate() {
        return commentDate;
    }


    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }


}
