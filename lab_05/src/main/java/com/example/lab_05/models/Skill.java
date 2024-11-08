package com.example.lab_05.models;

public class Skill {
    private String id;
    private String skillName;
    private String description;
    private String field;

    public Skill() {
    }

    public Skill(String id, String skillName, String description, String field) {
        this.id = id;
        this.skillName = skillName;
        this.description = description;
        this.field = field;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
