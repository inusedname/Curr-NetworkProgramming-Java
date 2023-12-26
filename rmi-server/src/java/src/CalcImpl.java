/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author QuangNV
 */
public class CalcImpl extends UnicastRemoteObject implements CalcInf {
    
    CalcImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.printf("a=%d b=%d", a, b);
        return a + b;
    }
    
}
