package shop.style.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.Entity.Historic;

import java.util.Optional;

public interface HistoricRepository extends MongoRepository<Historic, String> {

    Optional<Historic> findByUserId(Long user_id);
}
