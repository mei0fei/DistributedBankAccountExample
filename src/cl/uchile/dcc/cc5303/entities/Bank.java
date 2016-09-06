package cl.uchile.dcc.cc5303.entities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Bank extends UnicastRemoteObject implements IBank{

    private int money;
    private int txNumber;

    public final int fixedFastWithdrawal = 1000;

    public Bank() throws RemoteException {
        this.money = 0;
        this.txNumber = 0;
    }

    @Override
    public String getSaldo() throws RemoteException {
        ++this.txNumber;
        notifyOperation("Obtener saldo");
        return "Su saldo asciende a: $" + this.money;
    }

    @Override
    public void saveMoney(int money) throws RemoteException {
        ++this.txNumber;
        this.money += money;
        notifyOperation("Depositar dinero ("+money+")");
    }

    @Override
    public int fastWithdrawal() throws RemoteException {
        ++this.txNumber;
        this.money -= this.fixedFastWithdrawal;
        notifyOperation("Giro rapido por " + this.txNumber);
        return fixedFastWithdrawal;
    }

    private void notifyOperation(String operationName) {
        System.out.println("Operacion #"+this.txNumber+": "+operationName+" / Cuenta: "+this.money);
    }
}
