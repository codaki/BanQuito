/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.servicio;

/**
 *
 * @author mathi
 */
public class LoginService {
     public boolean login(String username, String password) {
        if ((username.equals("monster") && password.equals("774e993500f4027acfd72b7a7ee564b76ae43cf7c4c943ed0e0f364cca16b6ec")) || 
            (username.equals("admin") && password.equals("admin"))) {
            return true;
        }
        return false;
    }
}

