package ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.dto;

import java.util.List;

public class UserDetailsDTO {
    private List<Long> favouriteApplications;
    private String userCode;

    public void setFavouriteApplications(List<Long> favouriteApplications) {
        this.favouriteApplications = favouriteApplications;
    }

    public List<Long> getFavouriteApplications() {
        return favouriteApplications;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }
}
