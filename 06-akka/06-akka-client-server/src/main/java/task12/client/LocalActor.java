package task12.client;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import task12.msg.ClientPriceQuestion;
import task12.msg.PriceResponseMsg;

public class LocalActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, productName -> {

                    ClientPriceQuestion clientPriceQuestion = new ClientPriceQuestion(productName);
                    getContext().actorSelection("akka://remote_system@127.0.0.1:2551/user/receptionist").tell(clientPriceQuestion,getSelf());


                })
                .match(PriceResponseMsg.class, s -> {
                    System.out.println(s);

                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}
