package my.rest.restController.repositories;

import my.rest.restController.entity.Tiket;
import org.springframework.data.repository.CrudRepository;

public interface TiketRepository extends CrudRepository<Tiket,Long> {

}
