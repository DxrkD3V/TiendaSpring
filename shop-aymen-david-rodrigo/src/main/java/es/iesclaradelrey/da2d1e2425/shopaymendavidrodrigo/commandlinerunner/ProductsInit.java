package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.commandlinerunner;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products.ProductService;
import org.springframework.boot.CommandLineRunner;

public class ProductsInit implements CommandLineRunner {
    private final ProductService productService;
    public ProductsInit(ProductService productService) {
        this.productService = productService;
    }
    @Override
    public void run(String... args) throws Exception {
     
    }
}
