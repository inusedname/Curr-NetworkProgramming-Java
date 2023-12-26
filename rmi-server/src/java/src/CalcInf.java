package src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalcInf extends Remote {
    int add(int a, int b) throws RemoteException;
    
    User getUser() throws RemoteException;
}
