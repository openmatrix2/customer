package com.openmatrix;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final String[] ITEMS = new String[] { "Coke", "Coffee", "Water" };

	@Value("${order.dispatcher.url}")
	private String orderDispatcherUrl;
	
	@Scheduled(fixedRateString = "${client.request.interval}")
	public void webClientCall() {
		RestTemplate template = new RestTemplate();
		String item = ITEMS[new Random().nextInt(ITEMS.length)];
		log.info("Requested 10 {}, remaining inventory is {}", item,
				template.getForEntity(orderDispatcherUrl, String.class, item).getBody());
	}
}
