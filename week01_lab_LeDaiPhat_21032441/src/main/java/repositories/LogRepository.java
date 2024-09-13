package repositories;

import entities.Account;
import entities.Log;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;


public class LogRepository implements IRepository<Log> {
    private final EntityManager em;
    private final EntityTransaction transaction;

    public LogRepository(){
        em = Persistence.createEntityManagerFactory("default").createEntityManager();
        transaction = em.getTransaction();
    }

    @Override
    public boolean add(Log log) {
        try {
            transaction.begin();
            em.persist(log);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Log log) {
        try {
            transaction.begin();
            em.merge(log);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Log find(String id) {
        try{
            Log log = em.find(Log.class, id);
            return log;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public Log findByAccountId(String accountId) {
        return em.createNamedQuery("Log.findByAccountIdAndLogoutTime", Log.class)
                .setParameter("accountId", accountId)
                .setParameter("logoutTime", Instant.EPOCH)
                .getSingleResult();
    }

    @Override
    public ArrayList<Log> findAll() {
        return (ArrayList<Log>) em.createQuery("select l from Log l", Log.class).getResultList();
    }
}
