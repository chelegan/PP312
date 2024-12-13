package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.model.Role;

import java.util.Set;

public interface RoleService {
    Role getRoleByName(String roleName);
    Set<Role> getSetOfRoles(String role1, String role2);
    void saveRole(Role role);


}
