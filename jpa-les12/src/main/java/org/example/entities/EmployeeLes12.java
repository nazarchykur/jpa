package org.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_les12")
public class EmployeeLes12 {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentLes12 department;

    public DepartmentLes12 getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentLes12 department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmployeeLes12{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}