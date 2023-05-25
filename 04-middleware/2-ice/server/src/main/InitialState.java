package main;

import com.zeroc.Ice.Identity;

public class InitialState {
    public static String devices[] = new String[]{"fridge/1","fridge/2","calc/1","tiltCamera/1","zoomCamera/1"};

    public static boolean hasElement(Identity id){
        String ids = id.category+"/"+id.name;
        for(String device : devices){
            if( device.equals(ids)) return true;
        }
        return false;
    }

}
