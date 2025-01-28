package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.ShoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Rating;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;

import java.util.Collection;
import java.util.Optional;

public interface ShoppingCartService {
    long count();
    void save(ShoppingCart shoppingCart);
    Collection<ShoppingCart> findAll();
    Optional<ShoppingCart> findByProductID(Long productId);
    ShoppingCart sumUnits(ShoppingCart shoppingCart);

}
