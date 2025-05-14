package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CartDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;

import java.util.Collection;
import java.util.Optional;

public interface ShoppingCartService {
    long count();
    void save(ShoppingCart shoppingCart);
    Collection<ShoppingCart> findAll();
    Optional<ShoppingCart> findByProductID(Long productId);

    void sumUnits(ShoppingCart shoppingCart);
    Double sumTotalPrice(Collection<ShoppingCart> shoppingCarts);
    void add(Product product);
    void remove(Long id);
    void removeAll(String email);
    CartDTO getCartByEmail(String email); //cuando tengamos el usuario tendremos que cambiar el metodo y pasarle el id del usuario
    void saveOrUpdate(Long productId, int addUnits, String userEmail) throws Exception;

    void delete(Long productId, String email);
}
