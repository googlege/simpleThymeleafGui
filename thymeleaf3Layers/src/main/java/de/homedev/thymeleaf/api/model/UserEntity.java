package de.homedev.thymeleaf.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Access(AccessType.FIELD)
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity extends AbstractMandantEntity implements UserDetails {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "users";

	@Id
	@TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", length = AppConstants.USERNAME_MAX_LENGTH, nullable = false)
	private String username;

	@Column(name = "password", length = AppConstants.USERPASSWORD_MAX_LENGTH, nullable = false)
	private String password;

	@Column(name = "display_name", length = AppConstants.USERDISPLAYNAME_MAX_LENGTH, nullable = false)
	private String displayName;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private UserRoleEntity role;

	public UserEntity() {
		super();
	}

	public UserEntity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRoleEntity getRole() {
		return role;
	}

	public void setRole(UserRoleEntity role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", deleted=" + isDeleted() + ", version=" + getVersion()
				+ ", userRole=" + role + "]";
	}

	@Override
	public List<UserRoleEntity> getAuthorities() {

		List<UserRoleEntity> result = new ArrayList<>(1);
		result.add(this.getRole());
		return result;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isDeleted();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isDeleted();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isDeleted();
	}

	@Override
	public boolean isEnabled() {
		return !isDeleted();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMandantName() {
		return this.getMandant().getName();
	}

}
