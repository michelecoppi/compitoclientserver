package compito;


public class MainClient 
{
    public static void main( String[] args )
    {
       Client client = new Client(7000, "localhost");

       client.connetti();
       client.comunica();
    }
}
