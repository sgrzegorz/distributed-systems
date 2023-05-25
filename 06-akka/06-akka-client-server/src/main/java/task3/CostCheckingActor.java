package task3;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import akka.pattern.AskTimeoutException;

import task3.msg.Msg;
import task3.msg.ProductMsg;

import java.time.Duration;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static akka.pattern.Patterns.ask;


public class CostCheckingActor extends AbstractActor {
    String id;

    public CostCheckingActor(String id) {
        this.id = id;
    }

    public CostCheckingActor() {
    }
//    final ActorRef child = getContext().actorOf(Props.create(ShoppingActor.class), "myChild");
//    final ActorRef child1 = getContext().actorOf(Props.create(ShoppingActor.class), "myChild1");

//    ActorRef target1;
//    ActorRef target2;


    @Override
    public void preStart() {
        context().actorOf(Props.create(ShoppingActor.class), "child1");
        context().actorOf(Props.create(ShoppingActor.class), "child2");
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ProductMsg.class, msg -> {
                    System.out.println("CostCheckingActor received");

                    final Duration duration = Duration.ofMillis(300);
                    CompletableFuture<Object> future1 = ask(context().child("child1").get(), " request", duration).toCompletableFuture();
                    CompletableFuture<Object> future2 = ask(context().child("child2").get(), "another request", duration).toCompletableFuture();

                    Msg msg1 = null;
                    Msg msg2 = null;
                    try {
                        msg1 = (Msg) future1.join();
                    } catch (CancellationException | CompletionException e) {
                        System.out.println(e.getClass());
//                        e.printStackTrace();
                    }

                    try {
                        msg2 = (Msg) future2.join();
                    } catch (CancellationException | CompletionException e) {
                        System.out.println(e.getClass());
//                        e.printStackTrace();
                    }


                    System.out.println(msg1 + " " + msg2);
                    Msg lowerPrice = Msg.getLowerPrice(msg1, msg2);
                    ProductMsg productMsg = new ProductMsg(lowerPrice.value, msg.productName);

                    getSender().tell(productMsg, getSelf());
                    getContext().stop(self());

                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(
                    10,
                    Duration.ofMinutes(1),
                    DeciderBuilder.matchAny(o -> SupervisorStrategy.escalate())
                            .build());

    @Override
    public SupervisorStrategy supervisorStrategy() {

        return strategy;
    }
}
