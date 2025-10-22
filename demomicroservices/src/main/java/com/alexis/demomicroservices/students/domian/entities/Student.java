package com.alexis.demomicroservices.students.domian.entities;

import java.util.UUID;

public class Student {
    private final UUID id;
    private final String name;
    private final String university;
    private final String email;
    private final Boolean active;

    public Student(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.university = builder.university;
        this.email = builder.email;
        this.active = builder.active;
    }

    public static Builder builder() { return new Builder();}

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getUniversity() { return university; }
    public String getEmail() { return email; }
    public Boolean getActive() { return active; }

    public static class Builder {
        private UUID id;
        private String name;
        private String university;
        private String email;
        private Boolean active;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder university(String university) {
            this.university = university;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder active(Boolean active) {
            this.active = active;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
