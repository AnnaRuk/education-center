package de.ait.ec.repositories;

import de.ait.ec.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Course, Long> {


    /*
    *
    *     <S extends T> S save(S entity);

    <S extends T> java.lang.Iterable<S> saveAll(java.lang.Iterable<S> entities);

    java.util.Optional<T> findById(ID id);

    boolean existsById(ID id);

    java.lang.Iterable<T> findAll();

    java.lang.Iterable<T> findAllById(java.lang.Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(java.lang.Iterable<? extends ID> ids);

    void deleteAll(java.lang.Iterable<? extends T> entities);

    void deleteAll();
}
    * */
}
