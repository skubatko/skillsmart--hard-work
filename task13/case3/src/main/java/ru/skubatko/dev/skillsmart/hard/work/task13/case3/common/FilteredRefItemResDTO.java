package ru.skubatko.dev.skillsmart.hard.work.task13.case3.common;

import lombok.Builder;

@Builder
public class FilteredRefItemResDTO {
    private Object id;
    private Object referenceId;
    private Object code;
    private Object brief;
    private Object name;
    private Object description;

    public void setId(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }

    public void setReferenceId(Object referenceId) {
        this.referenceId = referenceId;
    }

    public Object getReferenceId() {
        return referenceId;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getCode() {
        return code;
    }

    public void setBrief(Object brief) {
        this.brief = brief;
    }

    public Object getBrief() {
        return brief;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getName() {
        return name;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getDescription() {
        return description;
    }

    public static FilteredRefItemResDTO fromReferenceItemEntity(ReferenceItemEntity item) {
        return FilteredRefItemResDTO.builder()
            .id(item.getId())
            .referenceId(item.getReference().getId())
            .code(item.getCode())
            .brief(item.getBrief())
            .name(item.getName())
            .description(item.getDescription())
            .build();
    }
}
