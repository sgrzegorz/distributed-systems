package server;



import gen.Weather.Data;

import java.util.Random;
import java.util.Scanner;

public class ServerConsole extends Thread{
    String cities[] = new String[]{"Cracow","Warsaw","New York","Havana","Paris","Berlin","Italy","Palermo"};
    String strength[] = new String[]{"calm","strong","dangerous","very strong","not so strong","killing"};
    String whatToDo[] = new String[]{"keep calm","escape","hide","dont worry","go home","stay home","rescue yourself"};

    String vulcano[] = new String[]{"Etna","","Veuvius","Kakatoa","Mount Elbrus"};
    String seas[] = new String[]{"Baltic","Red Sea","","North Sea","Caribbean Sea"};

    NewService newService;
    public ServerConsole(NewService newService) {
        this.newService = newService;
    }


    public void run() {


        System.out.println("Type something for an new event to appear: ");
        Scanner scanner = new Scanner(System. in);
        Random r=new Random();
        while(true){
            String inputString = scanner.nextLine();
            if(inputString.equals("storm")){
                newService.stormQueue.add(Data.newBuilder()
                        .setStrength(strength[r.nextInt(strength.length)])
                        .setWhatToDo(whatToDo[r.nextInt(whatToDo.length)])
                        .setPlace(seas[r.nextInt(seas.length)]).build());
            }else if(inputString.equals("vulc")){
                newService.vulcQueue.add(Data.newBuilder()
                        .setStrength(strength[r.nextInt(strength.length)])
                        .setWhatToDo(whatToDo[r.nextInt(whatToDo.length)])
                        .setPlace(vulcano[r.nextInt(vulcano.length)]).build());
            }else if(inputString.equals("wind")){
                newService.windQueue.add(Data.newBuilder()
                        .setStrength(strength[r.nextInt(strength.length)])
                        .setWhatToDo(whatToDo[r.nextInt(whatToDo.length)])
                        .setPlace(cities[r.nextInt(cities.length)]).build());
            }else{
                System.out.println("storm vulc wind");
            }



        }



    }
}
