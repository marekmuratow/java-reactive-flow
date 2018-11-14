package pl.entito;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class CustomSubscriber implements Subscriber<String> {

	private final static int DEMAND = 1;

	private String name;

	CustomSubscriber(String name) {
		this.name = name;
	}

	private Flow.Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.out.println(name + " on subscribe: give me " + DEMAND);
		this.subscription = subscription;
		subscription.request(DEMAND);
	}

	@Override
	public void onNext(String item) {
		System.out.println(name + " subscriber.onNext(item) -> " + item);
		subscription.request(DEMAND);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println(name + " subscriber error: " + throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println(name + " subscriber: it was a pleasure to meet you ;)");
	}

}
