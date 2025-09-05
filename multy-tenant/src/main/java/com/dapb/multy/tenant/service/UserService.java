package com.dapb.multy.tenant.service;

import com.dapb.multy.tenant.entity.Users;

public interface UserService {

    Users createUser(Users user);

    Users login(Users user);
}
