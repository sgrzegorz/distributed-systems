package task3;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import task3.msg.ProductMsg;


import java.time.Duration;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;

public class WebsiteServer extends AllDirectives {

    static ActorSystem system = ActorSystem.create("routes");
    final Materializer materializer = ActorMaterializer.create(system);
    final ActorRef receptionist = system.actorOf(Props.create(ReceptionistActor.class), "receptionist");
    final ActorRef database = system.actorOf(Props.create(DatabaseActor.class), "database");

    public static void main(String[] args) throws Exception {

        // boot up server using the route as defined below
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        //In order to access all directives we need an instance where the routes are define.
        WebsiteServer app = new WebsiteServer();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 8080), materializer);

        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read(); // let it run until user presses return

        binding
                .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
                .thenAccept(unbound -> system.terminate()); // and shutdown when done

    }

    private Route createRoute() {
        return concat(
                get(() -> pathPrefix("price", () ->
                        path(PathMatchers.segment(), productName -> {

                                    final Duration duration = Duration.ofMillis(7000);

                                    ProductMsg productMsg = new ProductMsg(productName);
                                    database.tell(productMsg,null);
                                    CompletionStage<Object> completionStage = Patterns.askWithReplyTo(receptionist,replyTo->new ProductMsg(productName),duration);

                                    return onSuccess(completionStage,resp1 ->{
                                        return complete(resp1.toString());
                                    });

                                }
                        )
                )),
                get(() -> pathPrefix("review", () ->
                                path(PathMatchers.segment(), productName -> {
                                            System.out.println("Process review:");
                                            String url = String.format("https://www.opineo.pl/?szukaj=%s&s=2",productName);
                                            long timeout = 10000;

                                            CompletionStage<String> resp= Http.get(system).singleRequest(HttpRequest.create(url))
                                                    .thenCompose(response -> response.entity().toStrict(timeout, materializer))
                                                    .thenApply(entity -> entity.getData().utf8String())
                                                    .thenApply(html -> parseBody(html));


                                    return onSuccess(resp,resp1 ->{
                                        return complete(resp1);
                                    });
//
                                })
                ))

        );
    }

    public String parseBody(String body) {
        try {


            Document doc = Jsoup.parse(body);
            String title = doc.title();

            Element element = doc.getElementsByClass("pls").get(0);

            Element firstProduct = element.getElementsByClass("shl_i pl_i").get(0);
            if (firstProduct == null) {
                System.out.println("null");
            }

            Element pros = firstProduct.getElementsByClass("pl_attr").get(0);
            if (pros == null) {
                System.out.println("null pros");
            }

            String prosResoponse = "Zalety:\n";
            Elements li = pros.select("li"); // select all li from ul
            for (Element e : li) {
                prosResoponse += e.text() + "\n";
            }

            return prosResoponse;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Server encountered an error and could not handle task";
        }
    }

}