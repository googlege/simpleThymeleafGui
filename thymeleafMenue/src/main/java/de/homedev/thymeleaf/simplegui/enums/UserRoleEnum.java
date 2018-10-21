
package de.homedev.thymeleaf.simplegui.enums;

public enum UserRoleEnum {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");
    private final String authority;

    private UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public static UserRoleEnum getEnum(String authority) {
        for (UserRoleEnum e : UserRoleEnum.values()) {
            if (e.getAuthority().equalsIgnoreCase(authority)) {
                return e;
            }
        }
        return null;
    }

}
