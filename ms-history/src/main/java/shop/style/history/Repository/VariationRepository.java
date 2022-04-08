package shop.style.history.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.history.Entity.Variation;

public interface VariationRepository extends MongoRepository<Variation, String> {
}
