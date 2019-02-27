package com.fulgent.data.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dogs_tbl")
public class DogEntity implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long did;

	@Column(length=150, name = "bread")
	private String breed;

	@Column(length=200, name = "imgUrl")
	private String imgUrl;

	@Column(length=200, name = "description")
	private String description;

	@Column(length=200, name = "users")
	@OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "dog", fetch = FetchType.EAGER, orphanRemoval = true)
	private List<UserFavoritedEntity> users;
	
	
	public DogEntity(){
		
	}

	public DogEntity(String breed, String imgUrl, String description) {
		this.breed = breed;
		this.imgUrl = imgUrl;
		this.description = description;
	}

	public long getDid() {
		return did;
	}

	public void setDid(long did) {
		this.did = did;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserFavoritedEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserFavoritedEntity> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "DogEntity [did=" + did + ", breed=" + breed + ", imgUrl=" + imgUrl + ", description=" + description
				+ "]";
	}

}
