package shop.style.catalog.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.catalog.Entity.Variation;

import java.util.List;
import java.util.Optional;

public interface VariationRepository extends MongoRepository<Variation, Long> {

    List<Variation> findAll();

    Optional<Variation> findById(Long id);
}
