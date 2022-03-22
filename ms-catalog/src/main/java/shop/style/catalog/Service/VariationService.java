package shop.style.catalog.Service;

import shop.style.catalog.DTO.Form.VariationFormDTO;
import shop.style.catalog.DTO.VariationDTO;

import java.util.List;

public interface VariationService {

    VariationDTO searchVariation(String  id);

    VariationDTO saveVariation(VariationFormDTO body);

    VariationDTO updateVariation(String id, VariationFormDTO body);

    void deleteVariation(String  id);
}
