package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.commandlinerunner;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Product;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Categories.CategoryService;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Products.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ObjectsInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final ProductService productService;

    public ObjectsInit(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        String descripcion4x4 = "Conquista cualquier terreno con nuestros vehículos 4x4, diseñados para quienes buscan aventura sin límites. Potentes y resistentes, estos coches te llevarán a lugares inaccesibles, garantizando una conducción cómoda y segura en todo tipo de caminos. Ya sea en montañas, desiertos o caminos de tierra, un 4x4 te ofrece el control necesario para superar cualquier desafío. Perfecto para quienes necesitan un compañero fiel en sus exploraciones más extremas. ¡Atrévete a desafiar los límites con un 4x4!";
        String descripcionelectricos = "Experimenta el futuro de la conducción con nuestros vehículos eléctricos, diseñados para ofrecerte un rendimiento excepcional mientras cuidas del medio ambiente. Con tecnología de punta, estos coches proporcionan una conducción silenciosa, eficiente y libre de emisiones, sin comprometer la potencia ni la autonomía. Ideales para quienes buscan una conducción limpia, moderna y con estilo, nuestros vehículos eléctricos están aquí para transformar tu experiencia al volante.";
        String descripciondeportivos = "Siente la adrenalina al volante de nuestros exclusivos coches deportivos, diseñados para ofrecerte una experiencia de conducción inigualable. Con motores de alto rendimiento, diseño aerodinámico y tecnología de vanguardia, estos vehículos están hechos para los que buscan velocidad y emoción en cada curva. Ya sea en la pista o en la carretera, estos coches te ofrecen un control total y una sensación de libertad absoluta. Atrévete a vivir la velocidad con estilo y precisión.";
        String descripcionfamiliares = "Viaja con comodidad y seguridad en nuestros vehículos familiares, diseñados para acompañarte en cada aventura en familia. Con amplios espacios, tecnología avanzada y características de seguridad excepcionales, nuestros coches familiares ofrecen el equilibrio perfecto entre confort, versatilidad y rendimiento. Ya sea para viajes largos o trayectos urbanos, disfrutarás de una conducción suave y relajante, con el respaldo de una marca que cuida de lo que más importa: tu familia.";
        String descripcionluxo = "Déjate seducir por el lujo y la exclusividad con nuestros vehículos de alta gama. Cada detalle de estos coches está diseñado para brindarte la máxima comodidad y elegancia, desde sus materiales premium hasta sus prestaciones tecnológicas de última generación. Con un diseño sofisticado y una conducción impecable, nuestros coches de lujo son la elección perfecta para aquellos que buscan lo mejor en cada viaje. Disfruta de una experiencia de conducción única, donde la perfección es la norma.";

        Category category1 = new Category("4x4", descripcion4x4,"/imagenes/4x4.jpg");
        Category category2 = new Category("Electricos", descripcionelectricos,"/imagenes/electricos.jpg");
        Category category3 = new Category("Deportivos", descripciondeportivos,"/imagenes/deportivos.jpg");
        Category category4 = new Category("Familiares", descripcionfamiliares,"/imagenes/familiares.jpg");
        Category category5 = new Category("Lujo", descripcionluxo,"/imagenes/lujo.jpg");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
        categoryService.save(category4);
        categoryService.save(category5);


        Product product1 = new Product(null, "Toyota Land Cruiser","https://scene7.toyota.eu/is/image/toyotaeurope/LCR0012a_24_WEB:Large-Landscape?ts=1712237347791&resMode=sharp2&op_usm=1.75,0.3,2,0&fmt=png-alpha", "Robusto 4x4 con capacidad todoterreno.", 45000.00, "Toyota", category1);
        Product product2 = new Product(null, "Jeep Wrangler","https://cdn.prod.website-files.com/5ec85520c4dfff034b036be2/65c2a025c92733315983325b_wrangler3.webp", "Clásico todoterreno ideal para aventuras.", 42000.00, "Jeep", category1);

        Product product3 = new Product(null, "Ford S-Max","https://images.prismic.io/carwow/50be31d1-da22-4d93-a972-7fa00cd51987_LHD+Ford+S-MAX+2020+exterior-05.jpg", "Familiar espacioso con gran capacidad.", 32000.00, "Ford", category2);
        Product product4 = new Product(null, "Renault Grand Scenic","https://www.km77.com/images/medium/2/6/8/3/renault-grand-scenic-2021-frontal.352683.jpg", "Familiar versátil con tecnología avanzada.", 30000.00, "Renault", category2);

        Product product5 = new Product(null, "Tesla Model 3","https://cdn-drivek-datak.motork.net/configurator-imgs/cars/es/1600/TESLA/MODEL-3/43043_SEDAN-4-DOORS/tesla-model-3-front-view.jpg", "Eléctrico con autonomía y tecnología avanzada.", 50000.00, "Tesla", category3);
        Product product6 = new Product(null, "Nissan Leaf","https://images.forococheselectricos.com/image/l/1320w/wp-content/uploads/2017/11/170905215242-2018-nissan-leaf-front-1024x576.jpg", "Compacto eléctrico con cero emisiones.", 28000.00, "Nissan", category3);

        Product product7 = new Product(null, "Porsche 911","https://www.cnnbrasil.com.br/wp-content/uploads/sites/12/2024/05/Porsche-911-Carrera-2025-Hibrido-16.jpg?w=880", "Deportivo icónico con rendimiento superior.", 100000.00, "Porsche", category4);
        Product product8 = new Product(null, "Ferrari F8 Tributo","https://cdn.ferrari.com/cms/network/media/img/resize/6094000a8c09a35ca689fba0-ferrari-magazine-S3OmQ-vnzt.jpg", "Deportivo de lujo con diseño exclusivo.", 250000.00, "Ferrari", category4);

        Product product9 = new Product(null, "Rolls Royce Phantom","https://cdn.motor1.com/images/mgl/JOBqOJ/s1/phantom-scintilla-private-collection---0.jpg", "Lujo sin igual con detalles artesanales.", 450000.00, "Rolls Royce", category5);
        Product product10 = new Product(null, "Bentley Continental GT","https://hips.hearstapps.com/hmg-prod/images/continental-gt-and-gtc-s-1-1654518508.jpg", "Lujo y confort en un coupé elegante.", 350000.00, "Bentley", category5);

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
        productService.save(product5);
        productService.save(product6);
        productService.save(product7);
        productService.save(product8);
        productService.save(product9);
        productService.save(product10);

    }
}
