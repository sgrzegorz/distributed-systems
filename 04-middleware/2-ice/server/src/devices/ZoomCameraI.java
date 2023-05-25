package devices;

import com.zeroc.Ice.Current;
import gen.House.State;
import gen.House.ZoomCamera;

public class ZoomCameraI extends Device implements ZoomCamera {
    String position = "default position";

    @Override
    public void zoom(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        System.out.println("Camera was zoomed");
    }

    @Override
    public String getScreen(Current current) {
        printInfo( new Object() {}.getClass().getEnclosingMethod().getName(),current);
        return this.position;
    }
}
