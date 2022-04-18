package shop.style.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.Entity.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {
}
