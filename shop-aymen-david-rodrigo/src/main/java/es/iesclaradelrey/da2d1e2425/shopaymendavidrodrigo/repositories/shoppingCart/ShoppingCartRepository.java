package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.shoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends ListCrudRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByProductId(Long productId);
    Optional<ShoppingCart> findByProductIdAndUserIdEmail(Long productId, String email);
    List<ShoppingCart> findByUserIdEmail(String email);
}
