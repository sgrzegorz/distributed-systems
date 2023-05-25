package main;


import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import devices.CalcI;

public class Server {




    public void t1(String[] args)
    {
        int status = 0;
        Communicator communicator = null;

        try
        {
            // 1. Inicjalizacja ICE - utworzenie communicatora
            communicator = Util.initialize(args);

            // 2. Konfiguracja adaptera
            // METODA 1 (polecana produkcyjnie): Konfiguracja adaptera Adapter1 jest w pliku konfiguracyjnym podanym jako parametr uruchomienia serwera
            //Ice.ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");

            // METODA 2 (niepolecana, dopuszczalna testowo): Konfiguracja adaptera Adapter1 jest w kodzie źródłowym
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1", "tcp -h localhost -p 10000:udp -h localhost -p 10000");

            MyServantLocator myServantLocator  = new MyServantLocator(adapter);
            adapter.addServantLocator(myServantLocator,"all");
            adapter.addServantLocator(myServantLocator,"calc");
            adapter.addServantLocator(myServantLocator,"fridge");
            adapter.addServantLocator(myServantLocator,"tiltCamera");
            adapter.addServantLocator(myServantLocator,"zoomCamera");

            // 3. Stworzenie serwanta/serwantów
//            CalcI calcServant1 = new CalcI();
//            CalcI calcServant2 = new CalcI();


            // 4. Dodanie wpisów do tablicy ASM
//            adapter.add(calcServant1, new Identity("calc11", "calc"));
//            adapter.add(calcServant2, new Identity("calc22", "calc"));
//            Identity identity = new Identity("calc22", "calc");
//            identity.


            // 5. Aktywacja adaptera i przejście w pętlę przetwarzania żądań
            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        }
        catch (Exception e)
        {
            System.err.println(e);
            status = 1;
        }
        if (communicator != null)
        {
            // Clean up
            //
            try
            {
                communicator.destroy();
            }
            catch (Exception e)
            {
                System.err.println(e);
                status = 1;
            }
        }
        System.exit(status);
    }


    public static void main(String[] args)
    {
        Server app = new Server();
        app.t1(args);
    }
}
