package org.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department_les12")
public class DepartmentLes12 {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<EmployeeLes12> employees = new ArrayList<>();

    public List<EmployeeLes12> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeLes12> employees) {
        this.employees = employees;
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
        return "DepartmentLes12{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", employees=" + employees +    //  виключаємо це поле, бо буде помилка через рекурсію = емплої буде викликати департмент, а департемнт буде викликати емплої і так далі по колу...
                '}';
    }
}