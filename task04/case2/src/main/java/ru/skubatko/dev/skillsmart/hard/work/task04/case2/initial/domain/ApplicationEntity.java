package ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.domain;

import java.util.List;

public class ApplicationEntity {
    private List<CommentEntity> comments;

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }
}
