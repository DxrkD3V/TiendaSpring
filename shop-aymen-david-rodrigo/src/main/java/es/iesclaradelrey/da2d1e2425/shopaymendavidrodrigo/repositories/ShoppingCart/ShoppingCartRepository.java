package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.ShoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Collection;

public interface ShoppingCartRepository extends ListCrudRepository<ShoppingCart, Long> {
    Collection<ShoppingCart> findByProductId(Long productId);
}
