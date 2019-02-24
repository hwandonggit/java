package com.fulgent.data.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fulgent.data.repository.DogRepository;
import com.fulgent.data.repository.UserFavoritedRepository;
import com.fulgent.data.repository.entity.DogEntity;
import com.fulgent.data.repository.entity.UserFavoritedEntity;
import com.fulgent.data.rest.model.Dog;
import com.fulgent.data.rest.model.UserFavorited;
import com.fulgent.data.rest.model.VoteUpDown;
import com.fulgent.data.service.DogService;

@Service("DogServiceImpl")
@Scope("singleton")
public class DogServiceImpl implements DogService {
	
	@Autowired
    private DogRepository dogRepository;
	
	@Autowired
    private UserFavoritedRepository userFavoritedRepository;
	

	@Override
	public List<Dog> findDogsGroupByBreed() {
		 List<DogEntity> dogEntityList=dogRepository.findDogsGroupByBreed();
		 return convertDogEntityIntoDog(dogEntityList);
	}

	@Override
	public List<Dog> findDogsByBreed(String breedName) {
		 List<DogEntity> dogEntityList=dogRepository.findDogsByBreed(breedName);
		 return convertDogEntityIntoDog(dogEntityList);
	}

	@Override
	public String voteUpDown(VoteUpDown voteUpDown) {
		Optional<UserFavoritedEntity> userFavoritedEntity=userFavoritedRepository.findById(voteUpDown.getUserid());
		if(userFavoritedEntity==null) {
			Optional<DogEntity> dogEntity=dogRepository.findById(voteUpDown.getDid());
			UserFavoritedEntity favoritedEntity=new UserFavoritedEntity();
			favoritedEntity.setUserid(voteUpDown.getUserid());
			favoritedEntity.setVoted(voteUpDown.isVoted());
			favoritedEntity.setDoe(new Timestamp(new Date().getTime()));
			favoritedEntity.setDog(dogEntity.orElseGet(null));
			userFavoritedRepository.save(favoritedEntity);
			return "voted";
		}else{
			return "already voted";	
		}
	}

	@Override
	public Dog findDogDetail(long did) {
		Optional<DogEntity> dogEntity=dogRepository.findById(did);
		Dog dog=new Dog();
		List<UserFavorited> users=new ArrayList<>();
		BeanUtils.copyProperties(dogEntity, dog,"users");
		List<UserFavoritedEntity> favoritedEntities=dogEntity.orElseGet(null).getUsers();
		if(favoritedEntities!=null && favoritedEntities.size()>0){
		for(UserFavoritedEntity entity:favoritedEntities){
			UserFavorited favorited=new UserFavorited();
			BeanUtils.copyProperties(entity, favorited,"dog");
			users.add(favorited);
		}
		dog.setUsers(users);
		}
		return dog;
	}
	
	private List<Dog> convertDogEntityIntoDog(List<DogEntity> dogEntityList){
		 List<Dog> dogs=new ArrayList<>();
		 for(DogEntity dogEntity: dogEntityList){
			 Dog dog=new Dog();
			 BeanUtils.copyProperties(dogEntity, dog);
			 dogs.add(dog);
		 }
		return dogs;
	}

}
