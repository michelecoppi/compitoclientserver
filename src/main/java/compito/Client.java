package compito;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private int porta;
    private String indirizzoServer;

    private Socket server;

    private BufferedReader ricevi;
    private DataOutputStream invia;

    public Client(int porta, String indserver) {
        this.porta = porta;
        this.indirizzoServer = indserver;
    }

    public void connetti() {
        try {
            server = new Socket(indirizzoServer, porta);

            ricevi = new BufferedReader(new InputStreamReader(server.getInputStream()));
            invia = new DataOutputStream(server.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void comunica() {
        Scanner barra = new Scanner(System.in);
        String input;
        while (true) {
            try {
                System.out.println(ricevi.readLine());
                input = barra.nextLine();

                invia.writeBytes(input + "\n");

                if (input.equalsIgnoreCase("chiudi")) {
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        try {
            barra.close();
            ricevi.close();
            invia.close();
            server.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
