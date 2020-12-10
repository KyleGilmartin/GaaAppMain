package edu.itsligo.gaaappmain;

public class ModelUsers {
    String FullName, UserEmail, uid;

    public ModelUsers() {

    }

    public ModelUsers(String fullName, String userEmail, String uid) {
        FullName = fullName;
        UserEmail = userEmail;
        this.uid = uid;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

