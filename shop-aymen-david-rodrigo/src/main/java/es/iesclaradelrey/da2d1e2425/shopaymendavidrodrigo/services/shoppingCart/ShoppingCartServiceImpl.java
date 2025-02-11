package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.shoppingCart.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public void sumUnits(ShoppingCart shoppingCart) {
        shoppingCart.setUnits(shoppingCart.getUnits() + 1);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Double sumTotalPrice(Collection<ShoppingCart> shoppingCarts) {
        return shoppingCarts.stream()
                .mapToDouble(cart -> cart.getProduct().getPrice() * cart.getUnits())
                .sum();
    }

    @Override
    public void add(Long id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByProductId(id);

        if (shoppingCart.isPresent()) {
            sumUnits(shoppingCart.get());
        } else {
            ShoppingCart newShoppingCart = new ShoppingCart(1,shoppingCart.get().getProduct());
            shoppingCartRepository.save(newShoppingCart);
        }
    }

    public void remove(Long id) {
        Optional<ShoppingCart> shoppingCart = findByProductID(id);

        if(shoppingCart.isPresent()) {
            if(shoppingCart.get().getUnits() > 1) {
                shoppingCart.get().setUnits(shoppingCart.get().getUnits() - 1);
                shoppingCartRepository.save(shoppingCart.get());
            }else{
                shoppingCartRepository.delete(shoppingCart.get());
            }
        }
    }

    public void removeAll() {
        Collection<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        shoppingCartRepository.deleteAll(shoppingCarts);
    }
}
