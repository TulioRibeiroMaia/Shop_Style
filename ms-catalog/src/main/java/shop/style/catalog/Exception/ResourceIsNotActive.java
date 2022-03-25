package shop.style.catalog.Exception;

public class ResourceIsNotActive extends RuntimeException {
    public ResourceIsNotActive(String category_is_not_active) {

        super(category_is_not_active);
    }
}
