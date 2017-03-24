package com.bstirbat.reactive.benchmark;

import com.bstirbat.reactive.benchmark.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class BenchmarkRunnerApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BenchmarkRunnerApplication.class);

    private static final String REACTIVE_RUNNER = "reactive";

    private static final String SERVLET_RUNNER = "servlet";

    private static final String REACTIVE_URI = "http://localhost:8081/message/{id}";

    private static final String SERVLET_URI = "http://localhost:8082/message/{id}";

    private final RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(BenchmarkRunnerApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
        String selectedUri;
        int numberOfRequests;

        if (strings.length != 2) {
            System.out.println("Command: <type> <nr of requests>");
            return;
        }

        switch (strings[0]) {
            case REACTIVE_RUNNER:
                selectedUri = REACTIVE_URI;
                break;
            case SERVLET_RUNNER:
                selectedUri = SERVLET_URI;
                break;
            default:
                System.out.println("<type> should be reactive or servlet");
                return;
        }

        numberOfRequests = Integer.valueOf(strings[1]);
        if (numberOfRequests <= 0 || numberOfRequests > 100) {
            System.out.println("<nr of requests> should be between 1 and 100");
            return;
        }

        ExecutorService es = Executors.newFixedThreadPool(numberOfRequests);
        List<CompletableFuture> completableFutures = new ArrayList<>();

        System.out.println("Prepare to send " + numberOfRequests + " requests to " + strings[0]);

        long startTime = System.currentTimeMillis();
        for(int i = 1; i <= numberOfRequests; i++) {
            System.out.println("Supply async: " + i);
            final int messageId = i;
            CompletableFuture<Message> completableFuture = CompletableFuture.supplyAsync(() -> loadMessage(strings[0], selectedUri, messageId));
            completableFutures.add(completableFuture);
        }

        CompletableFuture<Void> combined = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));
        combined.get();
        long endTime = System.currentTimeMillis();

        System.out.println("Test finished. Last time (in milliseconds): " + (endTime - startTime));
    }


    private Message loadMessage(String service, String uri, int messageId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(messageId));

        LOGGER.info("Prepare to call service {} for id {}", service, messageId);
        Message message = restTemplate.getForObject(uri, Message.class, params);
        LOGGER.info("Finished call to service {} for id {}", service, messageId);

        return message;
    }
}
