package compito;


public class MainServer
{
    public static void main( String[] args )
    {
       Server server = new Server(7000);

       server.connetti();
       server.comunica();
    }
}