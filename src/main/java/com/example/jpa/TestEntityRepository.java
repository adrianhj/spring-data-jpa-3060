package com.example.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {

    String QUERY = """
        select
            t
        from
            TestEntity t
        where
            :enabled is null
        or t.enabled = :enabled
    """;

    @Query(QUERY)
    List<TestEntity> list(Boolean enabled);

}
