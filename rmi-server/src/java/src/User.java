/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;

/**
 *
 * @author QuangNV
 */
public class User implements Serializable {
    private static final long serialVersionUID = 917;
    public int id;
    public String name;
    
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
