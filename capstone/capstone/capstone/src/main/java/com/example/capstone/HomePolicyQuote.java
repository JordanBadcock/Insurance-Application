package com.example.capstone;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class HomePolicyQuote {

    @Id
    private int quoteId;
    private double homeValue;
    private int homeAge;
    private int heatingType;
    private double quoteTotal;

    // Getters and Setters


    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public double getHomeValue() {
        return homeValue;
    }

    public void setHomeValue(double homeValue) {
        this.homeValue = homeValue;
    }

    public int getHomeAge() {
        return homeAge;
    }

    public void setHomeAge(int homeAge) {
        this.homeAge = homeAge;
    }

    public int getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(int heatingType) {
        this.heatingType = heatingType;
    }

    public double getQuoteTotal() {
        return quoteTotal;
    }

    public void setQuoteTotal(double quoteTotal) {
        this.quoteTotal = quoteTotal;
    }
}
