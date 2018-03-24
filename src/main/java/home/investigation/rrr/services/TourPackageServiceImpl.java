package home.investigation.rrr.services;

import home.investigation.rrr.domain.TourPackage;
import home.investigation.rrr.repo.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourPackageServiceImpl implements TService<TourPackage> {

    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
        this.tourPackageRepository = tourPackageRepository;
    }

    public TourPackage createTourPackage(String code, String name) {
//        Optional<TourPackage> tourPackage = tourPackageRepository.findById(code);
//        return tourPackage.map(t -> {
//                return t;
//        }).orElse(tourPackageRepository.save(new TourPackage(code, name)));
        if (!tourPackageRepository.findById(code).isPresent()) {
            return tourPackageRepository.save(new TourPackage(code, name));
        } else {
            return null;
        }

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
