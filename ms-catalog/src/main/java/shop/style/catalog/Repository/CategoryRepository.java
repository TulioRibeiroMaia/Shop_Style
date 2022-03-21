package shop.style.catalog.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shop.style.catalog.Entity.Category;

public interface CategoryRepository extends MongoRepository<Category, Long> {
}
