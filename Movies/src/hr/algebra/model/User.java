/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author Next Design
 */
public class User {

    private int idUser;
    private String userName;
    private String password;
    private int roleID;

    public User(String userName, String password, int roleID) {
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
    }

    public User(int id, String userName, String password, int roleID) {
        this.idUser = id;
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
    }

    public User(int idUser, String userName, String password) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
    }
    
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }



}
