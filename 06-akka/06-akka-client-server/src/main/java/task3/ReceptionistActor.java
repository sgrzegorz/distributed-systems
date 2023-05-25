package task3;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import task3.msg.ProductMsg;

import java.time.Duration;

public class ReceptionistActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);



    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ProductMsg.class, msg -> {
                    System.out.println("ReceptionistActor get");

                    ActorRef actorRef =context().actorOf(Props.create(CostCheckingActor.class));
                    actorRef.tell(msg,getSender());
                })
                .matchAny(o -> log.info("ReceptionistActor received unknown message"))
                .build();

    }

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(
                    10,
                    Duration.ofMinutes(1),
                    DeciderBuilder
                            .matchAny(o -> SupervisorStrategy.escalate())
                            .build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }
}
