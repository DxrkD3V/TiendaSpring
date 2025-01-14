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

        Category todoterreno = new Category("4x4", descripcion4x4,"/imagenes/4x4.jpg");
        Category electricos = new Category("Electricos", descripcionelectricos,"/imagenes/electricos.jpg");
        Category deportivos = new Category("Deportivos", descripciondeportivos,"/imagenes/deportivos.jpg");
        Category familiares = new Category("Familiares", descripcionfamiliares,"/imagenes/familiares.jpg");
        Category lujo = new Category("Lujo", descripcionluxo,"/imagenes/lujo.jpg");

        categoryService.save(todoterreno);
        categoryService.save(electricos);
        categoryService.save(deportivos);
        categoryService.save(familiares);
        categoryService.save(lujo);


        Product product1 = new Product(null, "Toyota Land Cruiser","https://scene7.toyota.eu/is/image/toyotaeurope/LCR0012a_24_WEB:Large-Landscape?ts=1712237347791&resMode=sharp2&op_usm=1.75,0.3,2,0&fmt=png-alpha", "Robusto 4x4 con capacidad todoterreno.", 45000.00, "Toyota", todoterreno);
        Product product2 = new Product(null, "Jeep Wrangler","https://cdn.prod.website-files.com/5ec85520c4dfff034b036be2/65c2a025c92733315983325b_wrangler3.webp", "Clásico todoterreno ideal para aventuras.", 42000.00, "Jeep", todoterreno);

        Product product3 = new Product(null, "Ford S-Max","https://images.prismic.io/carwow/50be31d1-da22-4d93-a972-7fa00cd51987_LHD+Ford+S-MAX+2020+exterior-05.jpg", "Familiar espacioso con gran capacidad.", 32000.00, "Ford", familiares);
        Product product4 = new Product(null, "Renault Grand Scenic","https://www.km77.com/images/medium/2/6/8/3/renault-grand-scenic-2021-frontal.352683.jpg", "Familiar versátil con tecnología avanzada.", 30000.00, "Renault", familiares);

        Product product5 = new Product(null, "Tesla Model 3","https://cdn-drivek-datak.motork.net/configurator-imgs/cars/es/1600/TESLA/MODEL-3/43043_SEDAN-4-DOORS/tesla-model-3-front-view.jpg", "Eléctrico con autonomía y tecnología avanzada.", 50000.00, "Tesla", electricos);
        Product product6 = new Product(null, "Nissan Leaf","https://images.forococheselectricos.com/image/l/1320w/wp-content/uploads/2017/11/170905215242-2018-nissan-leaf-front-1024x576.jpg", "Compacto eléctrico con cero emisiones.", 28000.00, "Nissan", electricos);

        Product product7 = new Product(null, "Porsche 911","https://www.cnnbrasil.com.br/wp-content/uploads/sites/12/2024/05/Porsche-911-Carrera-2025-Hibrido-16.jpg?w=880", "Deportivo icónico con rendimiento superior.", 100000.00, "Porsche", deportivos);
        Product product8 = new Product(null, "Ferrari F8 Tributo","https://cdn.ferrari.com/cms/network/media/img/resize/6094000a8c09a35ca689fba0-ferrari-magazine-S3OmQ-vnzt.jpg", "Deportivo de lujo con diseño exclusivo.", 250000.00, "Ferrari", deportivos);


        Product product9 = new Product(null, "Rolls Royce Phantom","https://www.autonocion.com/wp-content/uploads/2018/11/Mansory-Rolls-Royce-Phantom-2018-25.jpg", "Lujo sin igual con detalles artesanales.", 450000.00, "Rolls Royce", lujo);
        Product product10 = new Product(null, "Bentley Continental GT","https://hips.hearstapps.com/hmg-prod/images/continental-gt-and-gtc-s-1-1654518508.jpg", "Lujo y confort en un coupé elegante.", 350000.00, "Bentley", lujo);

        Product product11 = new Product(null, "Land Rover Defender", "https://pictures.dealer.com/m/mocrevecoeurlr/0693/7c2016eae6d8fa09798d45758dd00143x.jpg", "Robusto 4x4 ideal para terrenos difíciles.", 55000.00, "Land Rover", todoterreno);
        Product product12 = new Product(null, "Toyota Hilux", "https://s2.glbimg.com/FK-W1TarrxErVVPZ8HcXczwDzeI=/1200x/smart/filters:cover():strip_icc()/i.s3.glbimg.com/v1/AUTH_cf9d035bf26b4646b105bd958f32089d/internal_photos/bs/2020/U/k/sf737dSDeaSEL2oyQldg/2019-11-18-hiluxmateria.jpg", "Fuerza y durabilidad en cualquier terreno.", 40000.00, "Toyota", todoterreno);
        Product product13 = new Product(null, "Mercedes-Benz G-Class", "http://carlook.net/data/db_photos/mercedes-benz/g-class_amg/w463f_f/mercedes-benz_g-class_amg_w463f_f_suv5d-1917.jpg", "Lujoso todoterreno con capacidad todoterreno insuperable.", 130000.00, "Mercedes-Benz", todoterreno);
        Product product14 = new Product(null, "Jeep Cherokee", "https://wallpaperaccess.com/full/2692213.jpg", "Un todoterreno versátil con gran capacidad.", 45000.00, "Jeep", todoterreno);

        Product product15 = new Product(null, "Peugeot 5008", "https://images.netdirector.co.uk/gforces-auto/image/upload/q_auto,c_crop,f_auto,fl_lossy,x_1403,y_1307,w_4344,h_2441/auto-client/0b05e4a0f276ccc6c70945a53ee2f221/2961047_kvru6m5i40.jpg", "Familiar espacioso con diseño moderno y eficiencia.", 38000.00, "Peugeot", familiares);
        Product product16 = new Product(null, "Volkswagen Touran", "https://www.topgear.com/sites/default/files/cars-car/carousel/2016/03/vw_7422.jpg", "Familiar compacto y eficiente con una excelente relación calidad-precio.", 32000.00, "Volkswagen", familiares);
        Product product17 = new Product(null, "Skoda Superb", "https://auto.ironhorse.ru/wp-content/uploads/2015/10/superb-3-lb-fl-front.jpg", "Familiar con acabados premium y amplio espacio interior.", 42000.00, "Skoda", familiares);
        Product product18 = new Product(null, "Honda CR-V", "https://www.hdcarwallpapers.com/walls/2018_honda_cr_v_4k_3-HD.jpg", "SUV familiar espacioso, con alta eficiencia y tecnología avanzada.", 35000.00, "Honda", familiares);

        Product product19 = new Product(null, "Audi Q4 e-tron", "https://s1.cdn.autoevolution.com/images/news/gallery/examining-the-all-new-q4-e-trons-available-powertrains-and-the-tech-behind-them_8.jpg", "Eléctrico con estilo y tecnología avanzada.", 55000.00, "Audi", electricos);
        Product product20 = new Product(null, "Ford Mustang Mach-E", "https://media.autoexpress.co.uk/image/private/s--IofBDo_C--/v1575319158/autoexpress/2019/11/ford_mustang_mach-e_04_1.jpg", "SUV eléctrico con gran autonomía y rendimiento.", 60000.00, "Ford", electricos);
        Product product21 = new Product(null, "BMW iX", "https://www.electrichybridvehicletechnology.com/wp-content/uploads/2021/06/P90422119-highRes.jpg", "Eléctrico de lujo con un diseño impresionante.", 70000.00, "BMW", electricos);
        Product product22 = new Product(null, "Hyundai Ioniq 5", "https://cdn.motor1.com/images/mgl/XxEO9/s3/hyundai-ioniq-5-right-hand-drive.jpg", "SUV eléctrico con diseño futurista y gran rendimiento.", 48000.00, "Hyundai", electricos);

        Product product23 = new Product(null, "McLaren 720S", "http://www.hdcarwallpapers.com/walls/mclaren_720s_coupe_2017_2-HD.jpg", "Deportivo de alto rendimiento y diseño aerodinámico.", 300000.00, "McLaren", deportivos);
        Product product24 = new Product(null, "Lamborghini Huracán", "http://www.autoguide.com/blog/wp-content/uploads/2016/08/2016-Lamborghini-Huracan-LP-610-4-Spyder-Review-5.jpg", "Deportivo italiano con diseño llamativo y motor potente.", 250000.00, "Lamborghini", deportivos);
        Product product25 = new Product(null, "Aston Martin Vantage", "https://abbhbhjlqr.cloudimg.io/images.autodaily.com.au/2021/06/Aston-Martin-Vantage-F1-Edition-7.jpg?w=2520", "Deportivo británico de lujo con rendimiento sin igual.", 175000.00, "Aston Martin", deportivos);
        Product product26 = new Product(null, "Chevrolet Corvette Stingray", "https://photo-voiture.motorlegend.com/hd/chevrolet-corvette-c7-stingray-coupe-6-2-v8-466ch-91384.jpg", "Deportivo estadounidense con un diseño agresivo y un motor V8.", 65000.00, "Chevrolet", deportivos);

        Product product27 = new Product(null, "BMW 7 Series", "https://media.whatcar.com/wc-image/2019-01/2019_bmw_7_series_front.jpg", "De lujo con tecnología avanzada y comodidad excepcional.", 90000.00, "BMW", lujo);
        Product product28 = new Product(null, "Porsche Panamera", "https://www.automobilesreview.com/gallery/2017-porsche-panamera/2017-porsche-panamera-05.jpg", "De lujo con un rendimiento de alta gama y un diseño impresionante.", 120000.00, "Porsche", lujo);
        Product product29 = new Product(null, "Jaguar XJ", "http://momentcar.com/images/jaguar-xj-super-v8-1.jpg", "Berlina de lujo con un diseño elegante y un interior lujoso.", 95000.00, "Jaguar", lujo);
        Product product30 = new Product(null, "Maserati Quattroporte", "https://cevpu.com/wp-content/uploads/2017/01/2017-Maserati-Quattroporte-GTS-GranSport-exterior-front-and-side.jpg", "De lujo con un rendimiento excepcional y un diseño único.", 120000.00, "Maserati", lujo);


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
        productService.save(product11);
        productService.save(product12);
        productService.save(product13);
        productService.save(product14);
        productService.save(product15);
        productService.save(product16);
        productService.save(product17);
        productService.save(product18);
        productService.save(product19);
        productService.save(product20);
        productService.save(product21);
        productService.save(product22);
        productService.save(product23);
        productService.save(product24);
        productService.save(product25);
        productService.save(product26);
        productService.save(product27);
        productService.save(product28);
        productService.save(product29);
        productService.save(product30);


    }
}
