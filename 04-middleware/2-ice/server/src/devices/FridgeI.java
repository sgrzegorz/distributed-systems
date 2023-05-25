package devices;

import com.zeroc.Ice.Current;
import gen.House.Fridge;
import gen.House.State;

import static gen.House.State.On;

public class FridgeI extends Device implements Fridge {
    int temperature = 4;

    @Override
    public int getTemperature(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);

        return this.temperature;
    }

    @Override
    public void setTemperature(int temperature,Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);

        this.temperature = temperature;
    }

}
