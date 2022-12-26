package org.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_les14")
public class ProductLes14 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    /*
        LOAD      -- @PostLoad
        UPDATE    -- @PreUpdate @PostUpdate
        REMOVE    -- @PreRemove @PostRemove
        PERSIST   -- @PrePersist @PostPersist
     */
    @PrePersist
    public void prePersist() {
        this.dateCreated = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }
    
    @PostLoad
    public void postLoad() {
        System.out.println("Entity " + this + " was loaded.");
    }
    
    @PreRemove
    public void preRemove() {
        System.out.println("Entity " + this + " will be removed.");
    }
    
    @PostRemove
    public void postRemove(){
        System.out.println("Entity " + this + " was removed.");
    }
        

    public LocalDateTime getDateCreated() {
        return dateCreated;
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

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "ProductLes14{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                '}';
    }
}