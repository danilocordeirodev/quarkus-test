package br.maned.wolf.service;

import br.maned.wolf.entity.UserEntity;
import br.maned.wolf.exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {


    public UserEntity createUser(UserEntity user) {
        UserEntity.persist(user);
        return user;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return UserEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(UUID userId) {
        return (UserEntity) UserEntity.findByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID userId, UserEntity userEntity) {
        var user = findById(userId);
        user.username = userEntity.username;
        UserEntity.persist(user);
        return user;
    }

    public void deleteById(UUID userId) {
        var user = findById(userId);
        UserEntity.deleteById(user.userId);

    }
}
