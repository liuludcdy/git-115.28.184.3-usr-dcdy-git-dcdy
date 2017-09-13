package com.dcdy.vote.biz.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bee.common.FileUtil;
import com.bee.common.Response;
import com.dcdy.vote.biz.VoteService;
import com.dcdy.vote.dao.VtBoard;
import com.dcdy.vote.dao.VtBoardMapper;
import com.dcdy.vote.dao.VtCategory;
import com.dcdy.vote.dao.VtCategoryExample;
import com.dcdy.vote.dao.VtCategoryMapper;
import com.dcdy.vote.dao.VtCompany;
import com.dcdy.vote.dao.VtCompanyExample;
import com.dcdy.vote.dao.VtCompanyMapper;
import com.dcdy.vote.dao.VtVoteLog;
import com.dcdy.vote.dao.VtVoteLogExample;
import com.dcdy.vote.dao.VtVoteLogMapper;

@Service
public class VoteServiceImpl extends BaseServiceImpl implements VoteService {
	
	private static final Logger logger = Logger.getLogger(VoteServiceImpl.class);
	
	@Autowired
	VtCategoryMapper vtCategoryDao;
	
	@Autowired
	VtCompanyMapper vtCompanyDao;	

	@Autowired
	VtVoteLogMapper vtVoteLogDao;
	
	@Autowired
	VtBoardMapper vtBoardDao;
	
	@Override
	public List<VtCategory> queryCategory() throws Exception {
		// TODO Auto-generated method stub
		List<VtCategory> resp = new ArrayList<VtCategory>();
		try {
			VtCategoryExample vtCategoryExam = new VtCategoryExample();
			VtCategoryExample.Criteria vtCategoryExamCri = vtCategoryExam.createCriteria();
			vtCategoryExam.setOrderByClause("id desc ");
			List<VtCategory> vtCategorys = vtCategoryDao.selectByExample(vtCategoryExam);
			return vtCategorys;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public List<VtCompany> findCompanyByCategoryId(String clientId, Integer sort, Integer status, Integer categoryId) throws Exception {
		// TODO Auto-generated method stub
		try {
			VtCompanyExample vtCompanyExam = new VtCompanyExample();
			VtCompanyExample.Criteria vtCompanyExamCri = vtCompanyExam.createCriteria();
			if(null!=status)
				vtCompanyExamCri.andStatusEqualTo(status);
			vtCompanyExamCri.andCategoryIdEqualTo(categoryId);
			if(null==sort||1==sort)
				vtCompanyExam.setOrderByClause(" id desc ");
			else if(2==sort)
				vtCompanyExam.setOrderByClause(" vote_num desc ");
			List<VtCompany> vtCompanys = vtCompanyDao.selectByExample(vtCompanyExam);
			if(null!=clientId) {
				VtVoteLogExample vtVoteLogExam = new VtVoteLogExample();
				VtVoteLogExample.Criteria vtVoteLogExamCri = vtVoteLogExam.createCriteria();
				vtVoteLogExamCri.andClientIdEqualTo(clientId);
				List<VtVoteLog> vtVoteLogs = vtVoteLogDao.selectByExample(vtVoteLogExam);
				Map x = new HashMap();
				for(int i=0;i<vtVoteLogs.size();i++) {
					x.put(vtVoteLogs.get(i).getCompanyId(), 1);
				}
				for(int i=0;i<vtCompanys.size();i++) {
					VtCompany vtCompany = vtCompanys.get(i);
					if(x.containsKey(vtCompany.getId())) 
						vtCompany.setStatus(1);
					else
						vtCompany.setStatus(0);
				}
			}
			return vtCompanys;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public Response<Integer> vote(String clientId, Integer companyId) throws Exception {
		// TODO Auto-generated method stub
		Response<Integer> resp = new Response();
		try {
			VtVoteLogExample vtVoteLogExam = new VtVoteLogExample();
			VtVoteLogExample.Criteria vtVoteLogExamCri = vtVoteLogExam.createCriteria();
			vtVoteLogExamCri.andClientIdEqualTo(clientId);
			vtVoteLogExamCri.andCompanyIdEqualTo(companyId);
			List<VtVoteLog> vtVoteLogs = vtVoteLogDao.selectByExample(vtVoteLogExam);
			if(vtVoteLogs.size()>0) {
				resp.setRespFailed("您已投过该单位票了！");
				return resp;
			}
			VtCompany vtCompany = vtCompanyDao.selectByPrimaryKey(companyId);
			VtCompany x = new VtCompany();
			x.setId(vtCompany.getId());
			x.setVoteNum(vtCompany.getVoteNum()+1);
			vtCompanyDao.updateByPrimaryKeySelective(x);
			VtBoard vtBoard = vtBoardDao.selectByPrimaryKey("total_vote");
			vtBoard.setVtVal(Integer.toString(Integer.parseInt(vtBoard.getVtVal())+1));
			vtBoardDao.updateByPrimaryKeySelective(vtBoard);
			VtVoteLog vtVoteLog = new VtVoteLog();
			vtVoteLog.setClientId(clientId);
			vtVoteLog.setCompanyId(companyId);
			vtVoteLogDao.insertSelective(vtVoteLog);
			Integer num = vtCompanyDao.selectByPrimaryKey(companyId).getVoteNum();
			resp.setBody(num);
			return resp;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public Response<Integer> applyJoin(String clientId, String companyName, Integer categoryId,
			String concatName, String concatMobile) throws Exception {
		// TODO Auto-generated method stub
		Response<Integer> resp = new Response();
		try {
			VtCompanyExample vtCompanyExam = new VtCompanyExample();
			VtCompanyExample.Criteria vtCompanyExamCri = vtCompanyExam.createCriteria();
			vtCompanyExamCri.andClientIdEqualTo(clientId);
			List<VtCompany> vtCompanys = vtCompanyDao.selectByExample(vtCompanyExam);
			if(vtCompanys.size()>0) {
				resp.setRespFailed("您已经申请加入了，不能重复申请！");
				return resp;
			}
			VtCompany vtCompany = new VtCompany();
			vtCompany.setCategoryId(categoryId);
			vtCompany.setCompanyName(companyName);
			vtCompany.setConcatMobile(concatMobile);
			vtCompany.setConcatName(concatName);
			vtCompany.setStatus(0);
			vtCompanyDao.insertSelective(vtCompany);
			resp.setBody(0);
			return resp;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public Integer queryTotalVoteNum() throws Exception {
		// TODO Auto-generated method stub
		try {
			String totalVote = vtBoardDao.selectByPrimaryKey("total_vote").getVtVal();
			return Integer.parseInt(totalVote);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public VtCategory findCategoryById(Integer categoryId) throws Exception {
		// TODO Auto-generated method stub
		return vtCategoryDao.selectByPrimaryKey(categoryId);
	}

	@Override
	public Response<Integer> addCompany(String companyName, Integer categoryId,
			Integer voteNum, MultipartFile companyImage, String path) throws Exception {
		// TODO Auto-generated method stub
		Response<Integer> resp = new Response();
		try {
			String fid = saveFileFromInputStream(companyImage, path);
			VtCompany vtCompany = new VtCompany();
			vtCompany.setCategoryId(categoryId);
			vtCompany.setCompanyName(companyName);
			vtCompany.setCompanyImage(fid);
			vtCompany.setVoteNum(voteNum);
			vtCompany.setStatus(1);
			vtCompanyDao.insertSelective(vtCompany);
			resp.setBody(0);
			return resp;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}
	
	public static String saveFileFromInputStream(MultipartFile mf, String basePath) throws IOException {
		String originalFileName = mf.getOriginalFilename();
		String fId =UUID.randomUUID().toString().replace("-", "")+"."+originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		InputStream stream = mf.getInputStream();
		FileOutputStream fs = null;
		try {
			if(basePath.lastIndexOf("/") != 0) {
				basePath = basePath + "/";
			}
			String c2 = "assets/images/s2";
			File f = new File(basePath + c2);
			if(!f.isDirectory()) f.mkdirs();
			fs = new FileOutputStream(basePath + c2 + "/" + fId);
			byte[] bf = new byte[1024 * 1024];
			int bs = 0;
			int br = 0;
			while ((br = stream.read(bf)) != -1) {
				bs += br;
				fs.write(bf, 0, br);
				fs.flush();
			}
			return "/" + c2 + "/" + fId;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if(fs!=null) fs.close();
			if(stream!=null)  stream.close();
		}
	}

	@Override
	public Response<Integer> delCompany(Integer companyId) throws Exception {
		// TODO Auto-generated method stub
		Response<Integer> resp = new Response();
		try {
			VtCompany vtCompany = vtCompanyDao.selectByPrimaryKey(companyId);
			resp.setBody(vtCompany.getCategoryId());
			vtCompanyDao.deleteByPrimaryKey(companyId);
			return resp;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public Response<Integer> modifyCompany(Integer companyId,
			String companyName, Integer categoryId, Integer voteNum,
			MultipartFile companyImage, String path) throws Exception {
		// TODO Auto-generated method stub
		Response<Integer> resp = new Response();
		try {
			VtCompany vtCompany = new VtCompany();
			vtCompany.setId(companyId);
			if(null!=companyImage && !companyImage.isEmpty()) {
				String fid = saveFileFromInputStream(companyImage, path);
				vtCompany.setCompanyImage(fid);
			}
			vtCompany.setCategoryId(categoryId);
			vtCompany.setCompanyName(companyName);
			vtCompany.setVoteNum(voteNum);
			vtCompany.setStatus(1);
			vtCompanyDao.updateByPrimaryKeySelective(vtCompany);
			resp.setBody(0);
			return resp;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public VtCompany findCompanyById(Integer companyId) throws Exception {
		// TODO Auto-generated method stub
		try {
			return vtCompanyDao.selectByPrimaryKey(companyId);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}

	@Override
	public Response<Integer> modifyCompanyStatus(Integer companyId,
			Integer status) throws Exception {
		// TODO Auto-generated method stub
		Response<Integer> resp = new Response();
		try {
			VtCompany vtCompany = new VtCompany();
			vtCompany.setId(companyId);
			vtCompany.setStatus(status);
			vtCompanyDao.updateByPrimaryKeySelective(vtCompany);
			vtCompany = vtCompanyDao.selectByPrimaryKey(companyId);
			resp.setBody(vtCompany.getCategoryId());
			return resp;
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}
	
}