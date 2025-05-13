package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.shoppingCart;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CartDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.CartItemDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.dto.ProductAPIDTO;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.ShoppingCart;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.exceptions.NotRemaningUnitsException;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.products.ProductRepository;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.shoppingCart.ShoppingCartRepository;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.users.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;
    private Exception EntityNotFoundException;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, AppUserRepository appUserRepository, AppUserRepository appUserRepository1) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.appUserRepository = appUserRepository;
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
    public void saveOrUpdate(Long productId, int addUnits, String userEmail) throws Exception {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("no se ha encontrado el producto de id " + productId));

        AppUser user = appUserRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("no se ha encontrado el Usuario"));

        ShoppingCart itemCart = shoppingCartRepository
                .findByProductIdAndUserIdEmail(productId, userEmail)
                .orElseGet(() -> {
                    ShoppingCart newCart = new ShoppingCart(0, product, user);
                    newCart.setAddAt(LocalDateTime.now());
                    newCart.setUpdateAt(LocalDateTime.now());
                    return newCart;
                });

        int remainingUnits = product.getStock() - itemCart.getUnits() - addUnits;
        if (remainingUnits < 0) {
            throw new NotRemaningUnitsException("No hay unidades suficientes en el stock para este producto. Solo quedan " +
                    (product.getStock() - itemCart.getUnits()));
        }

        itemCart.setUnits(addUnits + itemCart.getUnits());
        itemCart.setUpdateAt(LocalDateTime.now());
        shoppingCartRepository.save(itemCart);
    }



    @Override
    public void delete(Long productId, String email) {
        ShoppingCart itemCart = shoppingCartRepository
                .findByProductIdAndUserIdEmail(productId, email)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el producto con ID " + productId + " en el carrito del usuario " + email));

        if (itemCart.getUnits() > 1) {
            itemCart.setUnits(itemCart.getUnits() - 1);
            shoppingCartRepository.save(itemCart);
        } else {
            shoppingCartRepository.delete(itemCart);
        }
    }


    public CartDTO getCartByEmail(String email) {
        Collection<ShoppingCart> shoppingCartItems = this.findAll();

        List<CartItemDTO> items = shoppingCartItems.stream()
                .filter(item -> item.getUserId().getEmail().equals(email))
                .map(item -> {
                    Product product = item.getProduct();
                    int quantity = item.getUnits();
                    double unitPrice = product.getPrice();
                    double subtotal = unitPrice * quantity;
                    ProductAPIDTO productDTO = new ProductAPIDTO(
                            product.getId(),
                            product.getName(),
                            product.getImageurl(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getStock(),
                            product.getManufacture(),
                            product.getMotor(),
                            product.getHp(),
                            product.getMaxVelocity(),
                            product.getCategory().getId()
                    );
                    return new CartItemDTO(
                            product.getId(),
                            product.getName(),
                            quantity,
                            unitPrice,
                            subtotal,
                            product.getImageurl(),
                            quantity,
                            productDTO

                    );

                })
                .toList();

        int totalUnits = items.stream().mapToInt(CartItemDTO::getQuantity).sum();
        double totalPrice = items.stream().mapToDouble(CartItemDTO::getSubtotal).sum();

        return new CartDTO(items, totalUnits, totalPrice);
    }


}