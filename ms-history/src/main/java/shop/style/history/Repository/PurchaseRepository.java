package shop.style.history.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.history.Entity.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {
}
