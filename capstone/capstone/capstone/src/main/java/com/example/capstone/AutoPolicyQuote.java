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
public class AutoPolicyQuote {
    @Id
    private int quoteId;
    private double carValue;
    private int totalAccidents;
    private int driverAge;
    private int location;
    private double quoteTotal;

    // Getters and Setters

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public double getCarValue() {
        return carValue;
    }

    public void setCarValue(double carValue) {
        this.carValue = carValue;
    }

    public int getTotalAccidents() {
        return totalAccidents;
    }

    public void setTotalAccidents(int totalAccidents) {
        this.totalAccidents = totalAccidents;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(int driverAge) {
        this.driverAge = driverAge;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public double getQuoteTotal() {
        return quoteTotal;
    }

    public void setQuoteTotal(double quoteTotal) {
        this.quoteTotal = quoteTotal;
    }


}