package devices;

import com.zeroc.Ice.Current;
import gen.House.ListDevices;
import main.InitialState;

public class ListDevicesI extends Device implements ListDevices {
    @Override
    public String[] listAll(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        return InitialState.devices;
    }
}
