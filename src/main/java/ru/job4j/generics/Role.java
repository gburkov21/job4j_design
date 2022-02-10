package ru.job4j.generics;

public class Role extends Base {

    private String roleName;

    public Role(String id, String name) {
        super(id);
        this.roleName = name;
    }

    public String getRoleName() {
        return roleName;
    }
}