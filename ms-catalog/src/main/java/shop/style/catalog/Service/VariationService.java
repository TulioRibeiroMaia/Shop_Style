package shop.style.catalog.Service;

import shop.style.catalog.DTO.Form.VariationFormDTO;
import shop.style.catalog.DTO.VariationDTO;

public interface VariationService {

    VariationDTO saveVariation(VariationFormDTO body);

    VariationDTO searchVariation(String id);

    VariationDTO updateVariation(String id, VariationFormDTO body);

    void deleteVariation(String id);
}
