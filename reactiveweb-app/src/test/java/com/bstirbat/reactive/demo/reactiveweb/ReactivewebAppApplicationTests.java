package com.bstirbat.reactive.demo.reactiveweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactivewebAppApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSimpleFlux() {
		Flux<String> flux = Flux.just("orange", "apple", "banana");

		Flux<String> upper = flux
				.log()
				.map(String::toUpperCase);
	}

	@Test
	public void testSimpleFluxSubscribe() {
		Flux<String> flux = Flux.just("orange", "apple", "banana");

		Flux<String> upper = flux
				.log()
				.map(String::toUpperCase);

		upper.subscribe(System.out::println);
	}

	@Test
	public void testSimpleFluxSubscribe_CustomSubscriber() {
		Flux<String> flux = Flux.just("orange", "apple", "banana");

		Flux<String> upper = flux
				.log()
				.map(String::toUpperCase);

		upper.subscribe(new Subscriber<String>() {

			private long count = 0;
			private Subscription subscription = null;

			@Override
			public void onSubscribe(Subscription subscription) {
				this.subscription = subscription;
				subscription.request(2);
			}

			@Override
			public void onNext(String s) {
				count++;
				if (count == 2) {
					count = 0;
					subscription.request(2);
				}
			}

			@Override
			public void onError(Throwable throwable) {
				System.out.println("onError");
				throwable.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}
		});
	}

	@Test
	public void testSimpleFluxSubscribe_ParallelThread() {
		Flux<String> flux = Flux.just("orange", "apple", "banana");

		Flux<String> upper = flux
				.log()
				.map(String::toUpperCase)
				.subscribeOn(Schedulers.parallel());

		upper.subscribe();
	}

}
