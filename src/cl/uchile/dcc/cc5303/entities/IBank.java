package cl.uchile.dcc.cc5303.entities;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBank extends Remote{
    String getSaldo() throws RemoteException;
    void saveMoney(int money) throws RemoteException;
    int fastWithdrawal() throws RemoteException;
}
