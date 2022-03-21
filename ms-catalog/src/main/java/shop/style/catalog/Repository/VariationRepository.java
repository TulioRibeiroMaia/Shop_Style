package shop.style.catalog.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.catalog.Entity.Variation;

public interface VariationRepository extends MongoRepository<Variation, Long> {
}
