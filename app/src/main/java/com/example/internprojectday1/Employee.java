package com.example.internprojectday1;

public class Employee {
    String id;
    String name;
    String mobile;
    String email;
    String designation;
    String reportingTo;
    String DOJ;
    String rights;

    public Employee(String id, String name, String mobile, String email, String designation, String reportingTo, String DOJ, String rights) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.designation = designation;
        this.reportingTo = reportingTo;
        this.DOJ = DOJ;
        this.rights = rights;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(String reportingTo) {
        this.reportingTo = reportingTo;
    }

    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String DOJ) {
        this.DOJ = DOJ;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
