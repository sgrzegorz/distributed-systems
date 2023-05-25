import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Z1_DivideWorker extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private int count=0;

    @Override
    public AbstractActor.Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    String result = Divide(s);
                    getSender().tell("result: " + result, getSelf());
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    private String Divide(String s){
        String[] split = s.split(" ");
        int a = Integer.parseInt(split[1]);
        int b = Integer.parseInt(split[2]);
        count+=1;
        return (a/b) + " operation count: "+count;
    }
}
