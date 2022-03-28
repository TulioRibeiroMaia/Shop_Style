package shop.style.catalog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shop.style.catalog.DTO.Form.VariationFormDTO;
import shop.style.catalog.DTO.VariationDTO;
import shop.style.catalog.Service.VariationService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/variations")
public class VariationController {

    @Autowired
    private VariationService variationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VariationDTO saveVariation(@RequestBody @Valid VariationFormDTO body) {
        return variationService.saveVariation(body);
    }

    @GetMapping("/{id}")
    public VariationDTO searchByID(@PathVariable String id) {
        return variationService.searchVariation(id);
    }

    @PutMapping("/{id}")
    public VariationDTO updateVariation(@PathVariable String id, @RequestBody @Valid VariationFormDTO body) {
        return variationService.updateVariation(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVariation(@PathVariable String id) {
        variationService.deleteVariation(id);
    }
}
