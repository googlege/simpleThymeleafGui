package de.homedev.thymeleaf.simplegui.model;

import java.io.Serializable;

import javax.persistence.*;

@Access(AccessType.FIELD)
@MappedSuperclass
public abstract class AbstractMandantEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private MandantEntity mandant;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public MandantEntity getMandant() {
        return mandant;
    }

    public void setMandant(MandantEntity mandant) {
        this.mandant = mandant;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
