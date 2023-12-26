/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author QuangNV
 */
public interface CalcInf extends Remote {
    int add(int a, int b) throws RemoteException;
        
    User getUser() throws RemoteException;
}

