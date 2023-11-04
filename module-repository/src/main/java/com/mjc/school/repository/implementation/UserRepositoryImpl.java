package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.impl.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractDbRepository <UserModel, Long>{

    @Override
    void update(UserModel prevState, UserModel nextState) {
        if (nextState.getName() != null || !nextState.getName().isBlank()){
            prevState.setName(nextState.getName());
        }
        if (nextState.getUserName() != null || !nextState.getUserName().isBlank()){
            prevState.setUserName(nextState.getUserName());
        }
        if(nextState.getEmail() != null || !nextState.getEmail().isBlank()){
            prevState.setEmail(nextState.getEmail());
        }
        if (nextState.getPassword() != null || !nextState.getPassword().isBlank()){
            prevState.setPassword(nextState.getPassword());
        }
    }

    public boolean existByEmail(String email){
        long count = entityManager
                .createQuery("SELECT count (a) FROM UserModel a WHERE a.email = :email", UserModel.class)
                .setParameter("email", email)
                .getResultStream().count();
        System.out.println("existUserEmail");
//        Long count = (Long) typedQuery.getSingleResult();
        return count > 0;
    }

    public boolean existUserName(String userName){
        long count = entityManager
                .createQuery("SELECT count(a) FROM UserModel a WHERE a.userName = :userName", UserModel.class)
                .setParameter("userName", userName)
                .getResultStream().count();
        System.out.println("existUserName");

//        Long result = (Long) count.getSingleResult();

        return count > 0;
    }


}
