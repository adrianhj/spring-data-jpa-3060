package com.example.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.type.YesNoConverter;

@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    private long id;

    @Column(name = "is_enabled")
    @Convert(converter = YesNoConverter.class)
    private boolean enabled;

    protected TestEntity() {}

    public TestEntity(long id, boolean enabled) {
        this.id = id;
        this.enabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
