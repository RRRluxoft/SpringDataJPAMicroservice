package home.investigation.rrr.services;

import home.investigation.rrr.domain.Difficulty;
import home.investigation.rrr.domain.Region;
import home.investigation.rrr.domain.Tour;
import home.investigation.rrr.repo.TourPackageRepository;
import home.investigation.rrr.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                           String duration, String bullets, String keywords, String tourPackageName,
                           Difficulty difficulty, Region region) {

        return tourPackageRepository.findByName(tourPackageName)
            .map(tp -> {
                return tourRepository.save(new Tour(title, description,blurb, price, duration,
                    bullets, keywords, tp, difficulty, region));
            }).orElseThrow(() -> new RuntimeException("Tour package does not exist: " + tourPackageName));

    }

    public Iterable<Tour> lookup() {
        return tourRepository.findAll();
    }

    public long total() {
        return tourRepository.count();
    }
}
