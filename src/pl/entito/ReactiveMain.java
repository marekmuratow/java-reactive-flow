package pl.entito;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ReactiveMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		// create the publisher
		CustomPublisher publisher = new CustomPublisher();

		// create subscribers
		CustomSubscriber subscriberA = new CustomSubscriber("A");
		CustomSubscriber subscriberB = new CustomSubscriber("B");
		CustomSubscriber subscriberC = new CustomSubscriber("C");

		// append all published items
		StringBuffer publishedValues = new StringBuffer("");
		CompletableFuture<Void> consumed = publisher.consume((String s) -> publishedValues.append(s).append(" "));

		// subscribe subscribers
		publisher.subscribe(subscriberA);
		publisher.subscribe(subscriberB);
		publisher.subscribe(subscriberC);

		// push items
		publisher.submit("1");
		publisher.offer("2", (subscriber, value) -> true);
		publisher.offer("3", 5, TimeUnit.SECONDS, (subscriber, value) -> true);

		// close the publisher
		publisher.close();

		// wait for CompletableFuture
		consumed.get(500, TimeUnit.MILLISECONDS);

		// list pushed items
		System.out.println("published values: " + publishedValues);
	}
}
