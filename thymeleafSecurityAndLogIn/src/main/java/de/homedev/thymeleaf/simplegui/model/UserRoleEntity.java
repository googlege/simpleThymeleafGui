package de.homedev.thymeleaf.simplegui.model;

import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import de.homedev.thymeleaf.simplegui.enums.UserRoleEnum;

@Entity
@Access(AccessType.FIELD)
@Table(name = UserRoleEntity.TABLE_NAME)
public class UserRoleEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "role";

    @Id
    @TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "id")
    private Long id;

    @Column(name = "authority_name", length = AppConstants.AUTHORITY_MAX_LENGTH, nullable = false)
    private String authority = UserRoleEnum.REINIGUNGSKRAFT.getAuthority();

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    // @JoinColumn(name = "role_id", nullable = false)
    private List<UserEntity> userList;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public UserRoleEntity() {
        super();
    }

    public UserRoleEntity(String authority) {
        super();
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserRoleEnum getUserRole() {
        return UserRoleEnum.getEnum(authority);
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.authority = userRole.getAuthority();
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

}
