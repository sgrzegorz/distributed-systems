package task12.server;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.DeciderBuilder;
import akka.pattern.AskTimeoutException;
import task12.msg.ClientPriceQuestion;
import task12.msg.PriceMsg;
import task12.msg.PriceResponseMsg;

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
    public CostCheckingActor(){}
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
                .match(ClientPriceQuestion.class, msg -> {
                    System.out.println("CostCheckingActor received: "+msg.productName);

                    final Duration duration = Duration.ofMillis(300);
                    CompletableFuture<Object> future1 = ask(context().child("child1").get(), " request", duration).toCompletableFuture();
                    CompletableFuture<Object> future2 = ask(context().child("child2").get(), (String)"another request", duration).toCompletableFuture();

                    PriceMsg priceMsg1=null;
                    PriceMsg priceMsg2=null;
                    try{
                        priceMsg1 = (PriceMsg) future1.join();
                    }catch (CancellationException| CompletionException e){
                        System.out.println(e.getClass());
//                        e.printStackTrace();
                    }

                    try{
                        priceMsg2 = (PriceMsg) future2.join();
                    }catch (CancellationException| CompletionException e){
                        System.out.println(e.getClass());
//                        e.printStackTrace();
                    }


                    System.out.println(priceMsg1+ " "+priceMsg2);
                    PriceMsg lowerPrice =PriceMsg.getLowerPrice(priceMsg1,priceMsg2);
                    PriceResponseMsg priceResponseMsg = new PriceResponseMsg(lowerPrice.value,msg.productName);

                    getSender().tell(priceResponseMsg, getSelf());

                    getContext().stop(self());


                })
                .matchAny(o -> log.info("CostCheckingActor received unknown message"))
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
