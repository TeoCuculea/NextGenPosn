/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

/**
 *
 * @author barb_
 */
public class UserDetails {
    
    private Integer id;
    private String lastName;
    private String firstName;
    private String address;
    private String role;

    public UserDetails(Integer id, String lastName, String firstName, String address, String role) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }
    
    
}
