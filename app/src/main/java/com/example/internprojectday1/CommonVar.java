package com.example.internprojectday1;

public class CommonVar {
    public String uid;
    public String name;
    public String mobile;
    public String rights;
    public String reportingTo;
    public String email;

    public CommonVar(String uid, String name, String mobile, String rights, String reportingTo, String email) {
        this.uid = uid;
        this.name = name;
        this.mobile = mobile;
        this.rights = rights;
        this.reportingTo = reportingTo;
        this.email = email;
    }

    public CommonVar() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(String reportingTo) {
        this.reportingTo = reportingTo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
