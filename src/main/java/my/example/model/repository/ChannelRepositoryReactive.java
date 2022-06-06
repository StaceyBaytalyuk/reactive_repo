package my.example.model.repository;

import my.example.model.document.ChannelDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepositoryReactive extends ReactiveMongoRepository<ChannelDocument, String> {
}
