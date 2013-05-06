package org.mcxiaoke.fancooker.api.rest;

import java.util.List;

import org.mcxiaoke.fancooker.api.ApiException;
import org.mcxiaoke.fancooker.api.Paging;
import org.mcxiaoke.fancooker.dao.model.UserModel;


/**
 * @author mcxiaoke
 * @version 1.0 2012.02.23
 *
 */
public interface UsersMethods {
	
	public List<UserModel> getFriends(String id, Paging paging) throws ApiException;
	public List<UserModel> getFollowers(String id, Paging paging) throws ApiException;
	public List<UserModel> getUserRecommendation(Paging paging) throws ApiException;
	public UserModel ignoreUserRecommendation(String id) throws ApiException;
	public List<UserModel> getUsersByTag(String tag, Paging paging) throws ApiException;
	public List<String> getUserTags(String id) throws ApiException;
	public UserModel showUser(String id) throws ApiException;

}