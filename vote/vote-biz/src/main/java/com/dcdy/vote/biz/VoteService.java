package com.dcdy.vote.biz;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bee.common.Response;
import com.dcdy.vote.dao.VtCategory;
import com.dcdy.vote.dao.VtCompany;

public interface VoteService {
	
	public List<VtCategory> queryCategory() throws Exception;
	
	public Integer queryTotalVoteNum() throws Exception;
	
	public List<VtCompany> findCompanyByCategoryId(String clientId, Integer sort, Integer status, Integer categoryId) throws Exception;
	
	public VtCategory findCategoryById(Integer categoryId) throws Exception;
	
	public Response<Integer> vote(String clientId, Integer companyId) throws Exception;
	
	public Response<Integer> applyJoin(String clientId, String companyName, Integer categoryId, String concatName, String concatMobile) throws Exception;
	
	public Response<Integer> addCompany(String companyName, Integer categoryId, Integer voteNum, MultipartFile companyImage, String path) throws Exception;
	
	public Response<Integer> delCompany(Integer companyId) throws Exception;
	
	public Response<Integer> modifyCompany(Integer companyId, String companyName, Integer categoryId, Integer voteNum, MultipartFile companyImage, String path) throws Exception;
	
	public Response<Integer> modifyCompanyStatus(Integer companyId, Integer status) throws Exception;
	
	public VtCompany findCompanyById(Integer companyId) throws Exception;
}
