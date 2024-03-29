package com.thrillio.entities;

import com.thrillio.constants.KidFriendlyStatus;

public abstract class Bookmark {
    private long id;
    private String title;
    private String profileUrl;
    private String kidFriendlyStatus = KidFriendlyStatus.UNKNOWN;
    private User kidFriendlyMarkedBy;
    private User sharedBy;

    public String getKidFriendlyStatus() {
        return kidFriendlyStatus;
    }

    public void setKidFriendlyStatus(String kidFriendlyStatus) {
        this.kidFriendlyStatus = kidFriendlyStatus;
    }


    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public User getKidFriendlyMarkedBy() {
        return kidFriendlyMarkedBy;
    }

    public User getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(User sharedBy) {
        this.sharedBy = sharedBy;
    }

    public void setKidFriendlyMarkedBy(User kidFriendlyMarkedBy) {
        this.kidFriendlyMarkedBy = kidFriendlyMarkedBy;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public abstract boolean isKidFriendlyEligible();

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }
}
