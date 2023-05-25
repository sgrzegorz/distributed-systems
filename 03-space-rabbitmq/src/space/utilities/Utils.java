package space.utilities;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

public class Utils {
//    public static String getRandomId(){
//        long unixTime = Instant.now().getEpochSecond();
//        String uniqueID = UUID.randomUUID().toString();
//        return insert(uniqueID, "__", 4);
//
//    }
    static boolean debug =false;

    public static String getRandomId(){
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID.substring(0,7);

    }

    public static String getShorterForm(String id){
        return id.substring(0,7);
    }

    public static String insert(String bag, String marble, int index) {
        String bagBegin = bag.substring(0,index);
        String bagEnd = bag.substring(index);
        return bagBegin + marble + bagEnd;
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
