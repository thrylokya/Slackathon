package com.slackathon.whatifollow1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slackathon.whatifollow1.entities.Followers;



@Repository
public interface FollowersRepository extends JpaRepository<Followers, Integer>{
	
	public List<Followers> findFollowersForUser(String userId);
	public List<Followers> findIdByUserIdAndFollowerId(String userId, String followerId);
}

