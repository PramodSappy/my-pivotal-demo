package com.app.demo.model;

import java.io.Serializable;

/**
 * Created by e067411 on 10/30/17.
 */
public class UpdateUser implements Serializable{
    String email;
    String firstName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
