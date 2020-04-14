package sk.upjs.nosql_redis_repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository  extends CrudRepository<RedisStudent,Long> {
    List<RedisStudent> findByPriezvisko(String priezvisko);
}
