package com.ecommerce.sb_ecom.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min=5,message = "Street name must be atleast 5 characters")
    private String street;

    @NotBlank
    @Size(min=5,message = "Building name must be atleast 5 characters")
    private String buildingName;

    @NotBlank
    @Size(min=3,message = "City name must be atleast 3 characters")
    private String city;

    @NotBlank
    @Size(min=2,message = "State name must be atleast 2 characters")
    private String state;

    @NotBlank
    @Size(min=2,message = "Country name must be atleast 2 characters")
    private String country;

    @NotBlank
    @Size(min=2,message = "PinCode must be atleast 2 characters")
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address(Long addressId, String street, String buildingName, String city, String state, String country, String pincode) {
        this.addressId = addressId;
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
