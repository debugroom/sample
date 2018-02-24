package org.debugroom.sample.spring.cloud.function;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class DisplayFunction implements java.util.function.Function<Flux<String>, Flux<String>>{

	@Override
	public Flux<String> apply(Flux<String> t) {
		log.info(t.toString());
		/*
		t.subscribe(s -> {
			log.info(this.getClass().getName() + " : " + s);
		});
		 */
		return Flux.just("test");
	}

}
