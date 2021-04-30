package com.studentmng.usermanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
public class studentModel {
    @Transient
    public static final String SEQUENCE_NAME = "student_sequence";
    
    @Id
    private String studentId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Date createdAt;
    private Date modifiedAt;

    public studentModel(String studentId, String name, String email, String phone, String password, Date createdAt,
            Date modifiedAt) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public studentModel() {
    }
    
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
