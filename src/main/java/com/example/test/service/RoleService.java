package com.example.test.service;

import com.example.test.model.Role;

public interface RoleService {
    Role createByName(String name);

    Role getByName(String name);
}
