package task12.server;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import task12.msg.ClientPriceQuestion;

import java.time.Duration;

public class ReceptionistActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);



    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ClientPriceQuestion.class, msg -> {
                    getContext().actorSelection("/user/database").tell(msg,getSender());

                    ActorRef costCheckingActor =context().actorOf(Props.create(CostCheckingActor.class));
                    costCheckingActor.tell(msg,getSender());

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
