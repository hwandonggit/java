package com.fulgent.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.fulgent.data.repository.entity.UserFavoritedEntity;

public interface UserFavoritedRepository extends CrudRepository<UserFavoritedEntity, String> {
	

}
