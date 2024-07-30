package io.reflectoring.crud.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    private final ProductRepository productRepository;
    HashMap <String, Object> datos;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @GetMapping()
    public List<Product> getproduct(){
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
         datos= new HashMap<>();
        Optional<Product> res = productRepository.findProductByName(product.getName());


        if(res.isPresent()){
            datos.put("error",true);
            datos.put("message", "ya hay un producto conese nombre ");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        productRepository.save(product);
        datos.put("data",datos);
        datos.put("message", "Se guardo conexito ");
        return new ResponseEntity<>(
                product,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object>  deleteProduct(Long id){
        datos = new HashMap<>();
        boolean existe= this.productRepository.existsById(id);
        if(!existe){
            datos.put("error",true);
            datos.put("message", "noxixste ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );

        }
        productRepository.deleteById(id);
        datos.put("message", "productoeliminado");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
    public List<String> lista(){
        return List.of(
                "casa",
                "lucas",
                "juan"
        );
    }
}
