package com.example.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.Public.class)
    private Long id;

    @JsonView(View.Public.class)
    private String name;

    @JsonView(View.Private.class)
    private int salary;

    @JsonView(View.Public.class)
    private Long managerId;

    public static class View {
        public interface Public {}
        public interface Private extends Public {}
    }
}
