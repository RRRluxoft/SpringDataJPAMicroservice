package home.books.books.repo;

import home.books.books.model.WebsiteUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
//@Repository
public interface UserRepository<T extends WebsiteUser, ID extends Long> extends PagingAndSortingRepository<WebsiteUser, Long> {

    @RestResource(exported = false)
    void deleteById(ID id);

    T findByEmail(@Param("email") String email);

}
