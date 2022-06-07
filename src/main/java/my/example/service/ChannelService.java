package my.example.service;

import lombok.RequiredArgsConstructor;
import my.example.container.ChannelResponse;
import my.example.converter.ChannelConverter;
import my.example.model.document.ChannelDocument;
import my.example.model.repository.ChannelRepositoryReactive;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ChannelService  {

    private final ChannelRepositoryReactive channelRepositoryReactive;
    private final ChannelConverter channelConverter;

    public Flux<ChannelResponse> getAllChannels() {
        Flux<ChannelDocument> channels = channelRepositoryReactive.findAll();
        return Flux.just(channelConverter.toChannelResponse(channels));
    }
}