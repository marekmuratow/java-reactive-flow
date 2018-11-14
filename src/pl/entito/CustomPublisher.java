package pl.entito;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

public class CustomPublisher extends SubmissionPublisher<String> {

	@Override
	public void subscribe(Subscriber<? super String> subscriber) {
		super.subscribe(subscriber);
	}

}
