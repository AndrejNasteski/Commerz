package com.project.commerz.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adId;
    private String adName;
    private String adDescription;
    private Long price;
    private String phoneNumber;
    private String eMail;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Location location;

    public Ad() {
    }

    public Ad(String adName, String adDescription, Long price, Category category, Location location, String phoneNumber, String eMail) {
        this.adName = adName;
        this.adDescription = adDescription;
        this.price = price;
        this.category = category;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdDescription() {
        return adDescription;
    }

    public void setAdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
