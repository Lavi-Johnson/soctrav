package com.yanni.sotrav.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yanni.sotrav.dao.abstracts.GenericDaoImpl;
import com.yanni.sotrav.dao.abstracts.IUserDao;
import com.yanni.sotrav.models.User;

/**
 * Class UserDao
 * <br />
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO without any XML configuration and also provide the Spring 
 * exception translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository("userDao")
@Transactional
public class UserDao extends GenericDaoImpl<User, Long> implements IUserDao{
  
  /**
   * Method getAll
   * <br/>
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return getEntityManager().createQuery("from User").getResultList();
  }
  
  /**
   * Method getByEmail
   * <br/>
   * Return the user having the passed email.
   */
  public User getByEmail(String email) {
    return (User) getEntityManager().createQuery(
        "from User where user_email =:email")
        .setParameter("email", email)
        .getSingleResult();
  }
  
  public ArrayList<User> getUsersByName(String name) {
	    name="%"+name+"%";
	    System.out.println("the name is"+name);
	    return (ArrayList<User>) getEntityManager().createQuery(
	        "from User where CONCAT(first_name,' ',last_name) like :name") //CONCAT(first_name,' ',last_name)
	        .setParameter("name", name)
	        .getResultList();
  }

  @Override
  public User find(String identity) {
	// TODO Auto-generated method stub
	return getByEmail(identity);
  }

} // class UserDao
