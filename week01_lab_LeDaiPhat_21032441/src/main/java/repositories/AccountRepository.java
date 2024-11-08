package repositories;

import entities.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.Optional;

public class AccountRepository implements IRepository<Account>{
    private EntityManager em;

    EntityTransaction transaction = null;

    public AccountRepository() {
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        transaction = em.getTransaction();
    }
    @Override
    public boolean add(Account account) {
        try{
            transaction.begin();
            em.persist(account);
            transaction.commit();

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        try{
            transaction.begin();
            String query = "UPDATE Account a SET a.fullName = :fullName, a.password = :password, a.email = :email, a.phone = :phone, a.status = :status WHERE a.accountId = :accountId";
            em.createQuery(query)
                    .setParameter("fullName", account.getFullName())
                    .setParameter("password", account.getPassword())
                    .setParameter("email", account.getEmail())
                    .setParameter("phone", account.getPhone())
                    .setParameter("status", account.getStatus())
                    .setParameter("accountId", account.getAccountId())
                    .executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try{
            transaction.begin();
            Account account = em.find(Account.class, id);
            em.remove(account);
            transaction.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public Account find(String id) {
        try{
            Account account = em.find(Account.class, id);
            return account;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Account> findAll() {
        try{
            return (ArrayList<Account>) em.createQuery("SELECT a FROM Account a").getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Account login(String phone, String password){
        try{
            return (Account) em.createQuery("SELECT a FROM Account a WHERE a.phone = :phone AND a.password = :password")
                .setParameter("phone", phone)
                .setParameter("password", password)
                .getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean isAdmin(Account a){
        try{
            long count = (long) em.createQuery("SELECT COUNT(a) FROM Account a JOIN GrantAccess gr ON gr.id.accountId = a.accountId  Where a.accountId = :accountId AND gr.id.roleId = :roleId AND gr.isGrant = :isGrant")
                    .setParameter("accountId", a.getAccountId())
                    .setParameter("roleId", "admin")
                    .setParameter("isGrant", true)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Account> findByRole(String role){
        try{
            return (ArrayList<Account>) em.createQuery("SELECT a FROM Account a JOIN GrantAccess gr ON gr.id.accountId = a.accountId WHERE gr.id.roleId = :role")
                    .setParameter("role", role)
                    .getResultList();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
