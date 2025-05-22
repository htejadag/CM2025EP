package com.example.examenparcial.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListUsers implements Serializable{

    //aqui si quiero hacer con varios usuario
    User u1 = new User("diegocueva", "123456");

    List<User> listUser = new ArrayList<>();

    public ListUsers() {
        this.listUser.add(u1);
    }
}
