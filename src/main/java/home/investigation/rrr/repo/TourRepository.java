package home.investigation.rrr.repo;

import home.investigation.rrr.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TourRepository extends CrudRepository<Tour, Integer> {
    Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);
}
