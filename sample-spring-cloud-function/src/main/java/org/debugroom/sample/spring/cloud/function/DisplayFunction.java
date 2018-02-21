package org.debugroom.sample.spring.cloud.function;

import reactor.core.publisher.Flux;

public class DisplayFunction implements java.util.function.Function<Flux<String>, Flux<String>>{

	@Override
	public Flux<String> apply(Flux<String> t) {
		System.out.println(t.toString());
		return Flux.just("test");
	}

}
