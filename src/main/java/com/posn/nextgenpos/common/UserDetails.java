/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

/**
 *
 * @author barb_
 */
public class UserDetails implements java.io.Serializable {

    private Integer id;
    private String username;
    private String email;
    private String position;
    private boolean validate;
    public UserDetails(Integer id, String username, String email,  String position,boolean validate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.position = position;
        this.validate = validate;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

}
