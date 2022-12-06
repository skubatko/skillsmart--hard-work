package ru.skubatko.dev.skillsmart.hard.work.task13.case3.common;

import java.util.List;

public class ReferenceItemEntity {
    private List<RoleEntity> roles;
    private Object id;
    private FilteredRefItemResDTO reference;
    private Object code;
    private Object brief;
    private Object name;
    private Object description;

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public FilteredRefItemResDTO getReference() {
        return reference;
    }

    public void setReference(FilteredRefItemResDTO reference) {
        this.reference = reference;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getBrief() {
        return brief;
    }

    public void setBrief(Object brief) {
        this.brief = brief;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }
}
