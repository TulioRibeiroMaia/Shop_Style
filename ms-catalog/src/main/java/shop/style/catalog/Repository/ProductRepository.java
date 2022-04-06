package shop.style.catalog.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.catalog.Entity.Product;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByVariationsId(String id);
}
