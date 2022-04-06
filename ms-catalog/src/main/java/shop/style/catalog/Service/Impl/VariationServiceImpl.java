package shop.style.catalog.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.style.catalog.DTO.Form.VariationFormDTO;
import shop.style.catalog.DTO.VariationDTO;
import shop.style.catalog.Entity.Product;
import shop.style.catalog.Entity.Variation;
import shop.style.catalog.Exception.ResourceNotFoundException;
import shop.style.catalog.Repository.ProductRepository;
import shop.style.catalog.Repository.VariationRepository;
import shop.style.catalog.Service.VariationService;

import java.util.Optional;

@Service
public class VariationServiceImpl implements VariationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VariationRepository variationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public VariationDTO saveVariation(VariationFormDTO body) {
        Optional<Product> product = this.productRepository.findById(body.getProduct_id());

        Variation variationSave = modelMapper.map(body, Variation.class);
        Variation savedVariation = variationRepository.save(variationSave);

        product.get().getVariations().add(savedVariation);
        productRepository.save(product.get());

        VariationDTO variationDTO = modelMapper.map(savedVariation, VariationDTO.class);
        variationDTO.setProduct_id(product.get().getId());
        return variationDTO;
    }

    @Override
    public VariationDTO searchVariation(String id) {
        Optional<Variation> variation = variationRepository.findById(id);

        if (variation.isPresent()) {
            Optional<Product> byVariationsId = productRepository.findByVariationsId(variation.get().getId());
            VariationDTO variationDTO = modelMapper.map(variation.get(), VariationDTO.class);
            variationDTO.setProduct_id(byVariationsId.get().getId());

            return variationDTO;
        }
        throw new ResourceNotFoundException("ID " + id);
    }

    @Override
    public VariationDTO updateVariation(String id, VariationFormDTO body) {
        Optional<Variation> variation = this.variationRepository.findById(id);

        if (variation.isPresent()) {
            Variation updatedVariation = modelMapper.map(body, Variation.class);
            updatedVariation.setId(variation.get().getId());
            this.variationRepository.save(updatedVariation);

            return modelMapper.map(updatedVariation, VariationDTO.class);
        }
        throw new ResourceNotFoundException("ID " + id);
    }

    @Override
    public void deleteVariation(String id) {
        Optional<Variation> variation = this.variationRepository.findById(id);

        if (!variation.isPresent()) {
            throw new ResourceNotFoundException("ID " + id);
        }
        this.variationRepository.deleteById(variation.get().getId());
    }
}




















//    private Product verifyIfProductExists(String id) {
//        return this.productRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Product with this id: " + id + " doesn't exist!"));
//    }
//
//    private Variation verifyIfVariationExists(String id) {
//        return variationRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Variation with this id: " + id + " doesn't exist!"));
//    }

