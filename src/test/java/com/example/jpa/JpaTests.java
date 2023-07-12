package com.example.jpa;

import static org.assertj.core.api.Assertions.assertThatCode;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.query.TypedParameterValue;
import org.hibernate.type.BasicType;
import org.hibernate.type.BasicTypeRegistry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DisplayName("JPA Tests")
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class JpaTests {

    @Autowired
    TestEntityRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Nested
    @DisplayName("Spring Data JPA Repository")
    class SpringDataJpaRepositoryTests {

        @Test
        @DisplayName("Should query correctly when a null parameter is passed first")
        void list_nullParameterFirst() {
            assertThatCode(() -> repository.list(null)).doesNotThrowAnyException();
            assertThatCode(() -> repository.list(true)).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should query correctly when a non-null parameter is passed first")
        void list_nonNullParameterFirst() {
            assertThatCode(() -> repository.list(true)).doesNotThrowAnyException();
            assertThatCode(() -> repository.list(null)).doesNotThrowAnyException();
        }

    }

    @Nested
    @DisplayName("Entity Manager")
    class EntityManagerTests {

        @Test
        @DisplayName("Should query correctly when a null parameter is passed first")
        void em_nullParameterFirst() {
            assertThatCode(() -> {
                Query query = em.createQuery(TestEntityRepository.QUERY);
                query.setParameter("enabled", null).getResultList();
            }).doesNotThrowAnyException();
            assertThatCode(() -> {
                Query query = em.createQuery(TestEntityRepository.QUERY);
                query.setParameter("enabled", true).getResultList();
            }).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should query correctly when a non-null parameter is passed first")
        void em_nonNullParameterFirst() {
            assertThatCode(() -> {
                Query query = em.createQuery(TestEntityRepository.QUERY);
                query.setParameter("enabled", true).getResultList();
            }).doesNotThrowAnyException();
            assertThatCode(() -> {
                Query query = em.createQuery(TestEntityRepository.QUERY);
                query.setParameter("enabled", null).getResultList();
            }).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Should query correctly when a TypedParameterValue parameter is passed first")
        void em_typedParameterValueParameterFirst() {
            BasicTypeRegistry typeHelper = em.getEntityManagerFactory()
                .unwrap(SessionFactoryImplementor.class)
                .getTypeConfiguration()
                .getBasicTypeRegistry();

            BasicType<Boolean> type = typeHelper.getRegisteredType(Boolean.class);

            assertThatCode(() -> {
                Query query = em.createQuery(TestEntityRepository.QUERY);
                query.setParameter("enabled", new TypedParameterValue<>(type, null)).getResultList();
            }).doesNotThrowAnyException();
            assertThatCode(() -> {
                Query query = em.createQuery(TestEntityRepository.QUERY);
                query.setParameter("enabled", true).getResultList();
            }).doesNotThrowAnyException();
        }

    }

}
