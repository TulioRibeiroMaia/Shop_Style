package shop.style.catalog.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {

    @Id
    private String  id;

    private String name;

    private String description;

    private Boolean active = true;

    private Category category;

    @DBRef
    private List<Variation> variations = new ArrayList<>();
}
