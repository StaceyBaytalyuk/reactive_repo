package my.example.controller;

import lombok.RequiredArgsConstructor;
import my.example.container.ChannelResponse;
import my.example.service.ChannelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = APPLICATION_NDJSON_VALUE)
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping(value = "/channels/all")
    public Flux<ChannelResponse> getAllChannels() {
        //return channelService.getAllChannels(); // нужна только эта строка. внизу код для печати в консоль
        Flux<ChannelResponse> allChannels = channelService.getAllChannels();
        System.out.println("------------------In controller------------------");
        allChannels.subscribe(System.out::println);
        return allChannels;
    }

    @GetMapping(value = "/channels/generate")
    public Flux<ChannelResponse> generateChannels() {
        return channelService.generateChannels();
    }
}
