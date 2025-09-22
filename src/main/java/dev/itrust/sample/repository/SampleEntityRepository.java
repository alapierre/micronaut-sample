package dev.itrust.sample.repository;

import dev.itrust.sample.model.SampleEntity;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.CrudRepository;

import java.time.LocalDateTime;

@Repository
public interface SampleEntityRepository extends CrudRepository<SampleEntity, Long> {

    @Query(value = """
            SELECT se FROM SampleEntity se WHERE se.createdAt >= :from AND se.createdAt < :to
            """,
            countQuery = "SELECT count(se.id) FROM SampleEntity se WHERE se.createdAt >= :from AND se.createdAt < :to"
    )
    Page<SampleEntity> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

}
