package ru.skubatko.dev.skillsmart.hard.work.task04.case1.initial.domain;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsEntity {
    private List<ApplicationEntity> favouriteApplications;
    private String userCode;

    public <E> UserDetailsEntity(String xEmployeePIN, ArrayList<E> es) {

    }

    public List<ApplicationEntity> getFavouriteApplications() {
        return favouriteApplications;
    }

    public void setFavouriteApplications(List<ApplicationEntity> favouriteApplications) {
        this.favouriteApplications = favouriteApplications;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
