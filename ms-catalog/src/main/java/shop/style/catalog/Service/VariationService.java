package shop.style.catalog.Service;

import shop.style.catalog.DTO.Form.VariationFormDTO;
import shop.style.catalog.DTO.VariationDTO;

import java.util.List;

public interface VariationService {

    VariationDTO searchVariation(Long id);

    VariationDTO saveVariation(VariationFormDTO body);

    VariationDTO updateVariation(Long id, VariationFormDTO body);

    void deleteVariation(Long id);
}
