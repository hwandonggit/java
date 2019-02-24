package com.fulgent.data.service;

import java.util.List;

import com.fulgent.data.rest.model.Dog;
import com.fulgent.data.rest.model.VoteUpDown;

/**
 * 
 * @author ppaos
 *
 */
public interface DogService {
	public List<Dog> findDogsByBreed(String breedName);
	public String voteUpDown(VoteUpDown voteUpDown);
	public List<Dog> findDogsGroupByBreed();
	public Dog findDogDetail(long did);
	
}
