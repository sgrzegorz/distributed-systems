package task3;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import task12.database.Database;
import task3.msg.ProductMsg;

public class DatabaseActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ProductMsg.class, msg -> {
                    Database.insert(msg.productName);
                })
                .matchAny(o -> log.info("DatabaseActor received unknown message"))
                .build();
    }
}
