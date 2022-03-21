package shop.style.catalog.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.catalog.Entity.Product;

public interface ProductRepository extends MongoRepository<Product, Long> {
}
