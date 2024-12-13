package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional()
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getSetOfRoles(String role1, String role2) {
        Set<Role> roleSet = new HashSet<>();
        if (role1 != null) {
            roleSet.add(getRoleByName(role1));
        }
        if (role2 != null) {
            roleSet.add(getRoleByName(role2));
        }
        return roleSet;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

}
