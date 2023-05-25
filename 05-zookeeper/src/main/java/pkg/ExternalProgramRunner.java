package pkg;

import java.io.IOException;


public class ExternalProgramRunner {
    static Process child;
    static String path;


    public static void start() {
        try {
            child = Runtime.getRuntime().exec(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void stop() {
        if (child != null) {
            child.destroy();
        }
    }

}
