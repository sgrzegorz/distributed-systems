package task12.server;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import task12.msg.PriceMsg;

import java.util.concurrent.ThreadLocalRandom;

public class ShoppingActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    final int MIN_TIME =100;
    final int MAX_TIME =500;
    final int MIN_PRICE =1;
    final int MAX_PRICE =10;


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg->
                {

                    PriceMsg priceMsg = new PriceMsg(getSimulatedPrice());

                    sender().tell(priceMsg,self());
                })
                .matchAny(o -> log.info("received unknown message2"))
                .build();
    }


    private int getSimulatedPrice() throws InterruptedException {
        int time = ThreadLocalRandom.current().nextInt(MIN_TIME, MAX_TIME + 1);
        Thread.sleep(time);

        return ThreadLocalRandom.current().nextInt(MIN_PRICE,MAX_PRICE +1);
//        return time;

    }

}
