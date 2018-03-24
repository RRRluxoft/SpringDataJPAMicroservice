package home.investigation.rrr.services;

import home.investigation.rrr.domain.Difficulty;
import home.investigation.rrr.repo.TourPackageRepository;
import home.investigation.rrr.repo.TourRepository;
import home.investigation.rrr.domain.Region;
import home.investigation.rrr.domain.Tour;
import home.investigation.rrr.domain.TourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourServiceImpl implements TService<Tour> {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourServiceImpl(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
        this.tourPackageRepository = tourPackageRepository;
        this.tourRepository = tourRepository;
    }

    public Tour createTour(String title, String description, String blurb, Integer price,
                           String duration, String bullets, String keywords, String tourPackageCode,
                           Difficulty difficulty, Region region) {
        Optional<TourPackage> tourPackage = Optional.of(tourPackageRepository.findById(tourPackageCode))
            .orElseThrow(() -> new RuntimeException(String.format("Tour package does not exist %s", tourPackageCode)));

        return tourRepository.save(new Tour(title, description, blurb, price, duration,
            bullets, keywords, tourPackage.get(), difficulty, region));
    }

    public Iterable<Tour> lookup() {
        return tourRepository.findAll();
    }

    public long total() {
        return tourRepository.count();
    }
}
