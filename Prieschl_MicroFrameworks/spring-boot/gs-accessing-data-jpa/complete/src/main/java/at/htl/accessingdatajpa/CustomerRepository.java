package at.htl.accessingdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByFirstName(@Param("name") String firstName);
	List<Customer> findByLastName(@Param("name") String lastName);
}