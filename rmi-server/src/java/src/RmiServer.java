/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author QuangNV
 */
public class RmiServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        CalcImpl calc = new CalcImpl();
        Naming.rebind("rmi://localhost/cal11", calc);
        System.out.println("success");
    }
    
}
