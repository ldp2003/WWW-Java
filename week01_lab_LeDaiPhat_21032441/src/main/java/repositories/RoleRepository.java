package repositories;

import entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository implements IRepository<Role>{
    private EntityManager em;

    EntityTransaction transaction = null;

    public RoleRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        transaction = em.getTransaction();
    }
    @Override
    public boolean add(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Role find(String id) {
        try{
            Role role = new Role();
            em.find(Role.class, id);
            return role;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Role> findAll() {
        return null;
    }

    public ArrayList<String> getRoleNameByAccountId(String accountId){
        try{
            List<String> rolename = em.createNamedQuery("Role.getRoleNameByAccountID", String.class)
                    .setParameter("accountId", accountId)
                    .getResultList();
            return new ArrayList<>(rolename);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
