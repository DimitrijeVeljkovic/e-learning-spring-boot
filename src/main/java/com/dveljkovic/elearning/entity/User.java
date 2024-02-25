package com.dveljkovic.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_name")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="verification_code")
    @JsonIgnore
    private String verificationCode;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonIgnore
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonIgnore
    private List<InProgress> inProgressCourses;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonIgnore
    private List<Completed> completedCourses;

    public User() {

    }

    public User(String firstName, String lastName, String userName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<InProgress> getInProgressCourses() {
        return inProgressCourses;
    }

    public void setInProgressCourses(List<InProgress> inProgressCourses) {
        this.inProgressCourses = inProgressCourses;
    }

    public List<Completed> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<Completed> completedCourses) {
        this.completedCourses = completedCourses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", comments=" + comments +
                '}';
    }
}
