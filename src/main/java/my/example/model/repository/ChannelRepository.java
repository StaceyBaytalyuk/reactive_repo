package my.example.model.repository;

import my.example.model.document.ChannelDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends MongoRepository<ChannelDocument, String> {
    void deleteByDescription(String description);
}
