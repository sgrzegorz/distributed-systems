import client.Client;
import org.junit.Test;
import server.Server;

public class MainTest {

    @Test
    public void testSimple() throws Exception {

        int portNumber = 12345;
        String serverHostName ="local";
        Server server = new Server(portNumber, serverHostName);
        server.start();



        while(true){

        }
    }
}
