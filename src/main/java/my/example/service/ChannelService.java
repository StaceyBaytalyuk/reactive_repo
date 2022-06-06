package my.example.service;

import lombok.RequiredArgsConstructor;
import my.example.container.ChannelResponse;
import my.example.model.document.ChannelDocument;
import my.example.model.repository.ChannelRepositoryReactive;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ChannelService  {

    private final ChannelRepositoryReactive channelRepositoryReactive;

    public Flux<ChannelResponse> getAllChannels() {
        Flux<ChannelDocument> channels = channelRepositoryReactive.findAll();
        return Flux.just(toChannelResponse(channels));
    }

    public Flux<ChannelResponse> generateChannels() {
        ChannelDocument first = ChannelDocument.builder()
                .uid("1")
                .description("first")
                .system(false)
                .build();

        ChannelDocument second = ChannelDocument.builder()
                .uid("2")
                .description("second")
                .system(false)
                .build();

        ChannelDocument system = ChannelDocument.builder()
                .uid("3")
                .description("system")
                .system(true)
                .build();

        return Flux.just(
                toChannelResponse(
                        channelRepositoryReactive.saveAll(List.of(first, second, system))));
    }

    private ChannelResponse toChannelResponse(Flux<ChannelDocument> documents) {
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