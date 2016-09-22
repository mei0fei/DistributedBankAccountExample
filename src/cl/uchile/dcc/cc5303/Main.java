package cl.uchile.dcc.cc5303;

import cl.uchile.dcc.cc5303.entities.IBank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) {


        try {
            IBank remoteBank = (IBank) Naming.lookup(Server.URL_SERVER);

            int optionSelected = 0;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Bienvenido al cajero automatico");
            while (optionSelected != 4){
                showCommands(System.out);
                try {
                    optionSelected = Integer.parseInt(br.readLine());
                }catch (IOException e) {
                    e.printStackTrace();
                }catch (NumberFormatException e){
                    System.out.println("Sólo se admiten números");
                }

                switch (optionSelected){
                    case 1:
                        System.out.println("Saldo: " + remoteBank.getSaldo());
                        break;
                    case 2:
                        System.out.println("Giro por: " + remoteBank.fastWithdrawal());
                        break;
                    case 3:
                        int money = 0;
                        try {
                            System.out.print("Especifique el monto a depositar: ");
                            money = Integer.parseInt(br.readLine());
                        }catch (IOException e) {
                            e.printStackTrace();
                        }catch (NumberFormatException e){
                            System.out.println("Sólo se admiten números");
                        }
                        remoteBank.saveMoney(money);
                        System.out.println("Deposito por " + money);
                        break;
                    case 4:
                        System.out.println("Salir");
                        break;
                    default:
                        System.out.println("Favor ingrese una opción válida");
                        break;
                }
            }


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public static void showCommands(PrintStream output){
        String msg = "Ingrese el número correspondiente a la opción deseada: \n";
        msg += "\t1 - Consulta de Saldo\n";
        msg += "\t2 - Giro\n";
        msg += "\t3 - Depósito\n";
        msg += "\t4 - Salir\n";
        output.println(msg);
    }
}
