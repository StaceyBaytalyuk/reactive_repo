package my.example.migration.postconstruct;

import lombok.RequiredArgsConstructor;
import my.example.model.document.ChannelDocument;
import my.example.model.repository.ChannelRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbInitializer {

    private final ChannelRepository channelRepository;

    @PostConstruct
    private void postConstruct() {
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
        channelRepository.saveAll(List.of(first, second, system));
    }
}