package testcases.C002D_ASSTOPRIVA__CWE766_496;

import java.awt.*;

public class UserRole implements Cloneable{

    private String name;
    private String role;

    public UserRole() {
    }

    public UserRole(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
