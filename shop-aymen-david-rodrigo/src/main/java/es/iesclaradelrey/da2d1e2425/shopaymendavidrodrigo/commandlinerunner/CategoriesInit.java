package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.commandlinerunner;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Category;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoriesInit implements CommandLineRunner {
    private final CategoryService categoryService;

    public CategoriesInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        String descripcion4x4 = "Conquista cualquier terreno con nuestros vehículos 4x4, diseñados para quienes buscan aventura sin límites. Potentes y resistentes, estos coches te llevarán a lugares inaccesibles, garantizando una conducción cómoda y segura en todo tipo de caminos. Ya sea en montañas, desiertos o caminos de tierra, un 4x4 te ofrece el control necesario para superar cualquier desafío. Perfecto para quienes necesitan un compañero fiel en sus exploraciones más extremas. ¡Atrévete a desafiar los límites con un 4x4!";
        String descripcionelectricos = "Experimenta el futuro de la conducción con nuestros vehículos eléctricos, diseñados para ofrecerte un rendimiento excepcional mientras cuidas del medio ambiente. Con tecnología de punta, estos coches proporcionan una conducción silenciosa, eficiente y libre de emisiones, sin comprometer la potencia ni la autonomía. Ideales para quienes buscan una conducción limpia, moderna y con estilo, nuestros vehículos eléctricos están aquí para transformar tu experiencia al volante.";
        String descripciondeportivos = "Siente la adrenalina al volante de nuestros exclusivos coches deportivos, diseñados para ofrecerte una experiencia de conducción inigualable. Con motores de alto rendimiento, diseño aerodinámico y tecnología de vanguardia, estos vehículos están hechos para los que buscan velocidad y emoción en cada curva. Ya sea en la pista o en la carretera, estos coches te ofrecen un control total y una sensación de libertad absoluta. Atrévete a vivir la velocidad con estilo y precisión.";
        String descripcionfamiliares = "Viaja con comodidad y seguridad en nuestros vehículos familiares, diseñados para acompañarte en cada aventura en familia. Con amplios espacios, tecnología avanzada y características de seguridad excepcionales, nuestros coches familiares ofrecen el equilibrio perfecto entre confort, versatilidad y rendimiento. Ya sea para viajes largos o trayectos urbanos, disfrutarás de una conducción suave y relajante, con el respaldo de una marca que cuida de lo que más importa: tu familia.";
        String descripcionluxo = "Déjate seducir por el lujo y la exclusividad con nuestros vehículos de alta gama. Cada detalle de estos coches está diseñado para brindarte la máxima comodidad y elegancia, desde sus materiales premium hasta sus prestaciones tecnológicas de última generación. Con un diseño sofisticado y una conducción impecable, nuestros coches de lujo son la elección perfecta para aquellos que buscan lo mejor en cada viaje. Disfruta de una experiencia de conducción única, donde la perfección es la norma.";

        Category category1 = new Category(1L, "4x4", descripcion4x4,"/imagenes/4x4.jpg");
        Category category2 = new Category(2L, "Electricos", descripcionelectricos,"/imagenes/electricos.jpg");
        Category category3 = new Category(3L, "Deportivos", descripciondeportivos,"/imagenes/deportivos.jpg");
        Category category4 = new Category(4L, "Familiares", descripcionfamiliares,"/imagenes/familiares.jpg");
        Category category5 = new Category(5L, "Lujo", descripcionluxo,"/imagenes/lujo.jpg");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);
        categoryService.save(category4);
        categoryService.save(category5);

    }
}
