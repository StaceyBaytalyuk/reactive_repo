package my.example.migration.changelog;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import my.example.model.document.ChannelDocument;
import my.example.model.repository.ChannelRepository;

import java.util.List;

@ChangeUnit(id = "init-channels", order = "001", author = "mongock")
public class InitChannelsChangelog {

    @Execution
    public void execution(ChannelRepository repository) {
        ChannelDocument first = ChannelDocument.builder()
                .uid("1")
                .description("first")
                .build();

        ChannelDocument second = ChannelDocument.builder()
                .uid("2")
                .description("second")
                .build();

        ChannelDocument system = ChannelDocument.builder()
                .uid("3")
                .description("system")
                .build();

        repository.saveAll(List.of(first, second, system));
    }

    @RollbackExecution
    public void rollbackExecution(ChannelRepository repository) {
        repository.deleteAll();
    }
}