package com.example.emran;

public class PosoClass {
    private String imageUrl,Session,MS_No,fullName,phoneNumber,departmentName,lastDonation,bloodGroup,designation,DesignationType;

    public PosoClass() {
    }

    public PosoClass(String imageUrl, String session, String MS_No, String fullName, String phoneNumber, String departmentName, String lastDonation, String bloodGroup, String designation, String designationType) {
        this.imageUrl = imageUrl;
        Session = session;
        this.MS_No = MS_No;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.departmentName = departmentName;
        this.lastDonation = lastDonation;
        this.bloodGroup = bloodGroup;
        this.designation = designation;
        DesignationType = designationType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public String getMS_No() {
        return MS_No;
    }

    public void setMS_No(String MS_No) {
        this.MS_No = MS_No;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLastDonation() {
        return lastDonation;
    }

    public void setLastDonation(String lastDonation) {
        this.lastDonation = lastDonation;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignationType() {
        return DesignationType;
    }

    public void setDesignationType(String designationType) {
        DesignationType = designationType;
    }
}
