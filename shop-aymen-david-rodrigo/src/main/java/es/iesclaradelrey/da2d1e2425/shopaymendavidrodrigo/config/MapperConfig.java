package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class MapperConfig {
   @Bean
   public ModelMap modelMapper(){
        return new ModelMap();
    }
}
