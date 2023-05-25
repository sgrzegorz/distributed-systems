package devices;

import com.zeroc.Ice.Current;
import gen.House.State;
import gen.House.TiltCamera;

public class TiltCameraI extends Device implements TiltCamera {
    String position = "default position";


    @Override
    public String getScreen(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        return position;
    }


    @Override
    public void setScreen(String position, Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        this.position = position;

    }
}
