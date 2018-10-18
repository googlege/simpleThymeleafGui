package de.homedev.thymeleaf.simplegui.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = MandantEntity.TABLE_NAME)
public class MandantEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "mandant";

    @Id
    @TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = AppConstants.MANDANTNAME_MAX_LENGTH, nullable = false)
    private String name;

    @OneToMany(mappedBy = "mandant", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<UserEntity> userList;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
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
