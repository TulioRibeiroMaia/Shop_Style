package shop.style.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.Entity.Variation;

public interface VariationRepository extends MongoRepository<Variation, String> {
}
