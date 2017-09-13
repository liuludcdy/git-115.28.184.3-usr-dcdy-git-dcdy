package com.dcdy.vote.job;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dcdy.vote.dao.VtBoardMapper;
import com.dcdy.vote.dao.VtCategoryExample;
import com.dcdy.vote.dao.VtCategoryMapper;
import com.dcdy.vote.dao.VtCompanyMapper;

public class RandomVoteJob {
	
	private static final Logger logger = Logger.getLogger(RandomVoteJob.class);
	
	@Autowired
	VtCategoryMapper vtCategoryDao;
	
	@Autowired
	VtCompanyMapper vtCompanyDao;
	
	@Autowired
	VtBoardMapper vtBoardDao;
	
	public void execute() {
		try {
			
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}
}
