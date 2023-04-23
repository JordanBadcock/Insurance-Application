package com.example.capstone;

public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private HomePolicyQuote homePolicyQuote;
    private AutoPolicyQuote autoPolicyQuote;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HomePolicyQuote getHomePolicyQuote() {
        return homePolicyQuote;
    }

    public void setHomePolicyQuote(HomePolicyQuote homePolicyQuote) {
        this.homePolicyQuote = homePolicyQuote;
    }

    public AutoPolicyQuote getAutoPolicyQuote() {
        return autoPolicyQuote;
    }

    public void setAutoPolicyQuote(AutoPolicyQuote autoPolicyQuote) {
        this.autoPolicyQuote = autoPolicyQuote;
    }
}