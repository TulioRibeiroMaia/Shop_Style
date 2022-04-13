package shop.style.history.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.history.Entity.Historic;

import java.util.Optional;

public interface HistoricRepository extends MongoRepository<Historic, String> {

    Optional<Historic> findByUserId(Long user_id);
}
