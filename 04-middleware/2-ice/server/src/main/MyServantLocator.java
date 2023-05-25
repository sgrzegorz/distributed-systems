package main;

import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.ObjectNotExistException;
import com.zeroc.Ice.ServantLocator;
import devices.*;

public class MyServantLocator implements ServantLocator {
    ObjectAdapter adapter;

    public MyServantLocator(ObjectAdapter adapter) {
        this.adapter = adapter;
    }

    public ServantLocator.LocateResult locate(com.zeroc.Ice.Current current) {
        System.out.println("ServantLocator locate");


        String name = current.id.name;
        String category = current.id.category;

        //list all available devices
        if(category.equals("all")){

            ListDevicesI servant4 = new ListDevicesI();
            adapter.add(servant4, new Identity(name, category));
            return new ServantLocator.LocateResult(servant4, null);
        }

        if (InitialState.hasElement(current.id)) {


            switch (category) {
                case ("calc"):
                    CalcI servant = new CalcI();
                    adapter.add(servant, new Identity(name, category));
                    return new ServantLocator.LocateResult(servant, null);

                case "fridge":
                    FridgeI servant1 = new FridgeI();
                    adapter.add(servant1, new Identity(name, category));
                    return new ServantLocator.LocateResult(servant1, null);

                case "tiltCamera":
                    TiltCameraI servant2 = new TiltCameraI();
                    adapter.add(servant2, new Identity(name, category));
                    return new ServantLocator.LocateResult(servant2, null);
                case "zoomCamera":
                    ZoomCameraI servant3 = new ZoomCameraI();
                    adapter.add(servant3, new Identity(name, category));
                    return new ServantLocator.LocateResult(servant3, null);

            }


        }

        System.out.println("Category not recognised");
        throw new ObjectNotExistException();
}

    public void finished(com.zeroc.Ice.Current current, com.zeroc.Ice.Object servant, java.lang.Object cookie) {
    }

    public void deactivate(String category) {
    }
}