package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.ratings;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.Rating;
import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.repositories.ratings.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService{
    private final RatingRepository ratingRepository;
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public long count() {
        return ratingRepository.count();
    }

    @Override
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public Collection<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Collection<Rating> findByProductID(Long productId) {
        return ratingRepository.findAll().stream().filter(rating -> rating.getProduct().getId().equals(productId)).collect(Collectors.toList());
    }
}
