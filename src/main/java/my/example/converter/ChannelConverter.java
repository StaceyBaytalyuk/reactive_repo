package my.example.converter;

import my.example.container.ChannelResponse;
import my.example.model.document.ChannelDocument;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class ChannelConverter {
    public ChannelResponse toChannelResponse(Flux<ChannelDocument> documents) {
        CompletableFuture<List<ChannelResponse.Channel>> future = documents.flatMap(
                c ->
                        Mono.just(
                                ChannelResponse.Channel.builder()
                                        .id(c.getUid())
                                        .name(c.getDescription())
                                        .system(c.getSystem())
                                        .build()
                        )
        ).collectList().toFuture();

        try {
            return ChannelResponse.builder().channels(future.get()).build();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}