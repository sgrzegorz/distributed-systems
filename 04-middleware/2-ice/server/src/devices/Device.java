package devices;


import com.zeroc.Ice.Current;
import gen.House.State;

import static gen.House.State.Off;
import static gen.House.State.On;

public class Device {
    State state = On;

    void printInfo(String methodName, Current current){
        System.out.println(current.id.category+ "/" + current.id.name +" " +methodName);
    }

    public void turnOn(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);

        this.state = On;
    }


    public void turnOff(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);

        this.state = Off;

    }

    public State getState(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);

        return this.state;

    }
}