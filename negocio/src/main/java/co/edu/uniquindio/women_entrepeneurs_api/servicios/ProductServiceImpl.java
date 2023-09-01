package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.ProductDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Product;
import co.edu.uniquindio.women_entrepeneurs_api.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product registerProduct(Product p) throws Exception {
        return productRepo.save(p);
    }

    @Override
    public Product updateProduct(Product p) throws Exception {
        return productRepo.save(p);
    }

    @Override
    public void deleteProduct(int id) throws Exception {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductDTO> findByNameOrPrice(String pattern) throws Exception {

        List<Product> filteredProducts = productRepo.finfByPattern(pattern);
        return filteredProducts.stream()
                .map(this::transformProduct)
                .collect(Collectors.toList());
    }
    private ProductDTO transformProduct(Product product) {
        ProductDTO transformedProduct = new ProductDTO(product.getId(),
                                                       product.getImage(),
                                                        product.getName(),
                                                        product.getDescription(),
                                                        product.getPrice(),
                                                        product.getIsAvailable(),
                                                        product.getStacks(),
                                                        product.getLimitDate(),
                                                        product.getIsActive());
        return transformedProduct;
    }
}
