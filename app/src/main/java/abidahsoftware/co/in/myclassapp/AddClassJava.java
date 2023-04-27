package abidahsoftware.co.in.myclassapp;

public class AddClassJava {
    String fullName, className, address, contact, staffName, subject_1,subject_2,subject_3,subject_4,
    city, state, message, imageUrl;

    public AddClassJava(String fullName, String className, String address, String contact, String staffName, String subject_1, String subject_2, String subject_3, String subject_4, String city, String state, String message, String imageUrl) {
        this.fullName = fullName;
        this.className = className;
        this.address = address;
        this.contact = contact;
        this.staffName = staffName;
        this.subject_1 = subject_1;
        this.subject_2 = subject_2;
        this.subject_3 = subject_3;
        this.subject_4 = subject_4;
        this.city = city;
        this.state = state;
        this.message = message;
        this.imageUrl = imageUrl;
    }

    public AddClassJava() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSubject_1() {
        return subject_1;
    }

    public void setSubject_1(String subject_1) {
        this.subject_1 = subject_1;
    }

    public String getSubject_2() {
        return subject_2;
    }

    public void setSubject_2(String subject_2) {
        this.subject_2 = subject_2;
    }

    public String getSubject_3() {
        return subject_3;
    }

    public void setSubject_3(String subject_3) {
        this.subject_3 = subject_3;
    }

    public String getSubject_4() {
        return subject_4;
    }

    public void setSubject_4(String subject_4) {
        this.subject_4 = subject_4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
