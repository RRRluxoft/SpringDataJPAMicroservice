package home.investigation.rrr.services;

import home.investigation.rrr.domain.TourPackage;
import home.investigation.rrr.repo.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourPackageServiceImpl implements TService {

    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
        return Optional.ofNullable(tourPackageRepository.findById(code))
            .orElse(Optional.of(new TourPackage(code, name))).get();
    }

    @Override
    public Iterable<TourPackage> lookup() {
        return tourPackageRepository.findAll();
    }

    @Override
    public long total() {
        return tourPackageRepository.count();
    }
}
