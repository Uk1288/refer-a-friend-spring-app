package com.referafriendspringapp.dto;

public class ReferralDTO {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReferralDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
