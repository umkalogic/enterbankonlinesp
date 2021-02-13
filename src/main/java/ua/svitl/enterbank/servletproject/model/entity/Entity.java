package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 3543992028985574919L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}