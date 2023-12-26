/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author QuangNV
 */
public class Client {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        CalcInf calc = (CalcInf) Naming.lookup("rmi://localhost/cal11");
        User user = calc.getUser();
        
        System.out.println(user.name);
    }
    
}