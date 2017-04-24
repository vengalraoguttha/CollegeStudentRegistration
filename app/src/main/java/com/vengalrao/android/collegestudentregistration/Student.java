package com.vengalrao.android.collegestudentregistration;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vengalrao on 24-04-2017.
 */

public class Student implements Parcelable {

    private String studentName;
    private String department;
    private String age;
    private String gender;
    private String phoneNo;
    private String mailId;

    public Student(){
        super();
    }

    public Student(Parcel in){
        readFromParcel(in);
    }

    public void setStudentName(String studentName){
        this.studentName=studentName;
    }

    public void setDepartment(String department){
        this.department=department;
    }

    public void setAge(String age){
        this.age=age;
    }

    public void setGender(String gender){
        this.gender=gender;
    }

    public void setPhoneNo(String phoneNo){
        this.phoneNo=phoneNo;
    }

    public void setMailId(String mailId){
        this.mailId=mailId;
    }

    public String getStudentName(){
        return studentName;
    }

    public String getDepartment(){
        return department;
    }

    public String getAge(){
        return age;
    }

    public String getGender(){
        return gender;
    }

    public String getPhoneNo(){
        return phoneNo;
    }

    public String getMailId(){
        return mailId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentName);
        dest.writeString(department);
        dest.writeString(age);
        dest.writeString(gender);
        dest.writeString(phoneNo);
        dest.writeString(mailId);
    }

    private void readFromParcel(Parcel in){
        studentName=in.readString();
        department=in.readString();
        age=in.readString();
        gender=in.readString();
        phoneNo=in.readString();
        mailId=in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR=new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
