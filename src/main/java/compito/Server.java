package compito;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private int porta;

    private ServerSocket server;
    private Socket client;

    private BufferedReader input;
    private DataOutputStream output;

    public Server(int porta) {
        this.porta = porta;
        try {
            server = new ServerSocket(porta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void connetti() {
        try {
            System.out.println("In attesa di un client ");
            client = server.accept();
            System.out.println("Client connesso! ");

            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void comunica() {
        String messaggio;
        ArrayList<String> lista = new ArrayList<>();

        try {
            output.writeBytes(
                "Inserisci la nota da memorizzare o digita LISTA per visualizzare le note salvate oppure chiudi per chiudere"
                        + "\n");
            while (true) {


                messaggio = input.readLine();
                if (messaggio.equalsIgnoreCase("lista")) {
                    for (int i = 0; i < lista.size(); i++) {
                        output.writeBytes((i+1)+")"+lista.get(i)+" ");
                       
                    }
                   output.writeBytes("\n");
                } else {

                    output.writeBytes("Nota salvata" + "\n");
                    lista.add(messaggio);
                }

                if (messaggio.equalsIgnoreCase("chiudi")) {
                    break;
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            input.close();
            output.close();
            client.close();
            server.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
