package io.reflectoring.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path  = "api/v1/product")

public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public  List<Product> getProducts(){
        return this.productService.getproduct();
    }
    @PostMapping
    public ResponseEntity<Object> registrarProducto(@RequestBody  Product product) {
        return this.productService.newProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> eliminar(@PathVariable("productId") Long id){
        return this.productService.deleteProduct(id);
    }
    @GetMapping("/lista")
    public List<String>  get(){
        return this.productService.lista();
    }

}
