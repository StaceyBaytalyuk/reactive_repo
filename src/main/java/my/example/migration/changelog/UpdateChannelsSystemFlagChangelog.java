package my.example.migration.changelog;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import my.example.model.repository.ChannelRepository;

@ChangeUnit(id = "update-channels_system-flag", order = "002", author = "mongock")
public class UpdateChannelsSystemFlagChangelog {

    @Execution
    public void execution(ChannelRepository repository) {
        repository.findAll().forEach(
                channel -> {
                    channel.setSystem( channel.getDescription().equals("system") );
                    repository.save(channel);
                });

        // раскомментировать этот код чтобы посмотреть как работает rollback
        // добавляются поля system, ждём 10 секунд, бросаем исключение,
        // вследсвие чего вызывается rollback и удаляются поля system

//        try {
//            Thread.sleep(10_000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        String line = "+++++++++++++++++++++++++++++++++++++";
//        throw new RuntimeException(line+"\nException to test Rollback\n"+line);
    }

    @RollbackExecution
    public void rollbackExecution(ChannelRepository repository) {
        repository.findAll().forEach(
                channel -> {
                    channel.setSystem(null);
                    repository.save(channel);
                });
    }
}