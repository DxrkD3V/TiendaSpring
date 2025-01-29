package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.ShoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.ShoppingCart.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public long count() {
        return shoppingCartRepository.count();
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Collection<ShoppingCart> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findByProductID(Long productId) {
        return shoppingCartRepository.findByProductId(productId).stream().findFirst();
    }

    @Override
    public ShoppingCart sumUnits(ShoppingCart shoppingCart) {
        shoppingCart.setUnits(shoppingCart.getUnits() + 1);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    public void remove(ShoppingCart shoppingCart) {
        shoppingCartRepository.delete(shoppingCart);
    }
}
