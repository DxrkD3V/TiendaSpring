package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CartDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CartItemDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.NotRemaningUnitsException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.products.ProductRepository;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.shoppingCart.ShoppingCartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private Exception EntityNotFoundException;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
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
    public void add(Product product) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByProductId(product.getId());

        if (shoppingCart.isPresent()) {
            sumUnits(shoppingCart.get());
        } else {
            ShoppingCart newShoppingCart = new ShoppingCart(1,product);
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

    @Override
    public void saveOrUpdate (Long productId, int addUnits) throws Exception {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("no se ha encontrado el producto de id "+productId));

        ShoppingCart itemCart = shoppingCartRepository
                .findByProductId(productId)
                .orElse(new ShoppingCart(0,product));


        int remaningUnits = product.getStock() - itemCart.getUnits() - addUnits;
        if(remaningUnits < 0) {
            throw new NotRemaningUnitsException("No hay unidades suficientes en el stock para este producto. Solo quedan "+(product.getStock() - itemCart.getUnits()));
        }
        itemCart.setUnits(addUnits + itemCart.getUnits());
        shoppingCartRepository.save(itemCart);
    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("no se ha encontrado el producto de id "+productId));

        ShoppingCart itemCart = shoppingCartRepository
                .findByProductId(productId)
                .orElseThrow(() -> new EntityNotFoundException("no se ha encontrado el producto de id "+productId+" en el carrito"));

        if(itemCart.getUnits() > 1) {
            itemCart.setUnits(itemCart.getUnits() - 1);
        }else{
            shoppingCartRepository.delete(itemCart);
        }

    }

    public CartDTO getCartByUserId(String userId) {
        Collection<ShoppingCart> shoppingCartItems = this.findAll();

        List<CartItemDTO> items = shoppingCartItems.stream()
                .filter(item -> item.getUserId().equals(userId))
                .map(item -> {
                    Product product = item.getProduct();
                    int quantity = item.getUnits();
                    double unitPrice = product.getPrice();
                    double subtotal = unitPrice * quantity;

                    return new CartItemDTO(
                            product.getId(),
                            product.getName(),
                            quantity,
                            unitPrice,
                            subtotal
                    );
                })
                .toList();

        int totalUnits = items.stream().mapToInt(CartItemDTO::getQuantity).sum();
        double totalPrice = items.stream().mapToDouble(CartItemDTO::getSubtotal).sum();

        return new CartDTO(items, totalUnits, totalPrice);
    }


}
