package services;

import repositories.RoleRepository;

import java.util.ArrayList;

public class RoleService {
    private final RoleRepository roleRepository;

public RoleService() {
        roleRepository = new RoleRepository();
    }

    public ArrayList<String> getRoleName(String accountID){
        return roleRepository.getRoleNameByAccountId(accountID);
    }
}
