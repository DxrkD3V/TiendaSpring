package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.Ratings;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Rating;

import java.util.Collection;

public interface RatingService {
    long count();
    void save(Rating rating);
    Collection<Rating> findAll();
    Collection<Rating> findByProductID(Long productId);
}
