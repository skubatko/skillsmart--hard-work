package ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.domain;

import java.time.LocalDateTime;

public class CommentEntity {
    private String userCode;
    private String userLogin;
    private String comment;
    private LocalDateTime commentDate;
    private ApplicationEntity application;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    public ApplicationEntity getApplication() {
        return application;
    }
}
