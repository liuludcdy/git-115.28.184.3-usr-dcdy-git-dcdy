package com.vote.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bee.common.Response;
import com.dcdy.vote.biz.VoteService;
import com.dcdy.vote.dao.VtCategory;
import com.dcdy.vote.dao.VtCompany;
import com.vote.servlet.CookieUtil;

@Controller
@RequestMapping("")
public class VoteController {
	
	private static final Logger logger = Logger.getLogger(VoteController.class);
	
	@Autowired
	VoteService voteService;
	
	@RequestMapping(value = "")
	public String index(HttpServletRequest request,	
			ModelMap map) {
		try {
			List<VtCategory> vtCategorys = voteService.queryCategory();
			map.addAttribute("vtCategorys", vtCategorys);
			Integer totalVote = voteService.queryTotalVoteNum();
			map.addAttribute("totalVote", totalVote);
			return "/vote/index";
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "queryCompany")
	public String queryCompany(HttpServletRequest request,
			@RequestParam(value = "categoryId", required = true) Integer categoryId,
			ModelMap map) {
		try {
			String clientId = CookieUtil.getCookie(request, "clientId");
			List<VtCompany> vtCompanys = voteService.findCompanyByCategoryId(clientId, null, 1, categoryId);
			map.addAttribute("vtCompanys", vtCompanys);
			VtCategory vtCategory = voteService.findCategoryById(categoryId);
			map.addAttribute("vtCategory", vtCategory);
			return "/vote/companys";
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "ranking")
	public String ranking(HttpServletRequest request,
			@RequestParam(value = "categoryId", required = true) Integer categoryId,
			ModelMap map) {
		try {
			List<VtCompany> vtCompanys = voteService.findCompanyByCategoryId(null, 2, 1, categoryId);
			map.addAttribute("vtCompanys", vtCompanys);
			VtCategory vtCategory = voteService.findCategoryById(categoryId);
			map.addAttribute("vtCategory", vtCategory);
			return "/vote/ranking";
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "join",method= RequestMethod.GET)
	public String joinView(HttpServletRequest request,	
			ModelMap map) {
		try {
			List<VtCategory> vtCategorys = voteService.queryCategory();
			map.addAttribute("vtCategorys", vtCategorys);
			return "/vote/join";
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "join",method= RequestMethod.POST)
	@ResponseBody
	public Response join(HttpServletRequest request,
			@RequestParam(value = "companyName", required = true) String companyName,
			@RequestParam(value = "categoryId", required = true) Integer categoryId,
			@RequestParam(value = "concatName", required = true) String concatName,
			@RequestParam(value = "concatMobile", required = true) String concatMobile,
			ModelMap map) {
		try {
			String clientId = CookieUtil.getCookie(request, "clientId");
			Response<Integer> resp = voteService.applyJoin(clientId, companyName, categoryId, concatName, concatMobile);
			return resp;
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "joinOk",method= RequestMethod.GET)
	public String joinOk(HttpServletRequest request,	
			ModelMap map) {
		try {
			return "/vote/joinOk";
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "vote",method= RequestMethod.POST)
	@ResponseBody
	public Response vote(HttpServletRequest request,
			@RequestParam(value = "companyId", required = true) Integer companyId,
			ModelMap map) {
		try {
			String clientId = CookieUtil.getCookie(request, "clientId");
			Response<Integer> resp = voteService.vote(clientId, companyId);
			return resp;
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "admin",method= RequestMethod.GET)
	public String admin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "companyId", required = false) Integer companyId,
			ModelMap map) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if(!"202.85.220.36".equals(ip)) {
				response.getWriter().print("access forbit");
				return null;
			}
			if(null!=categoryId) {
				List<VtCompany> vtCompanys = voteService.findCompanyByCategoryId(null, null, null, categoryId);
				map.addAttribute("vtCompanys", vtCompanys);				
			}
			if(null!=companyId) {
				VtCompany vtCompany = voteService.findCompanyById(companyId);
				map.addAttribute("svtCompany", vtCompany);
			}
			List<VtCategory> vtCategorys = voteService.queryCategory();
			map.addAttribute("vtCategorys", vtCategorys);		
			return "/vote/admin";
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "admin/add",method= RequestMethod.POST)
	public String addCompany(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "categoryId", required = true) Integer categoryId,
			@RequestParam(value = "companyName", required = true) String companyName,
			@RequestParam(value = "voteNum", required = true) Integer voteNum,
			@RequestParam("companyImage") MultipartFile companyImage,
			ModelMap map) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if(!"202.85.220.36".equals(ip)) {
				response.getWriter().print("access forbit");
				return null;
			}
			voteService.addCompany(companyName, categoryId, voteNum, companyImage, request.getRealPath("/"));
			return "redirect:/admin?categoryId="+categoryId;
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "admin/delete",method= RequestMethod.GET)
	public String delCompany(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "companyId", required = true) Integer companyId,
			ModelMap map) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if(!"202.85.220.36".equals(ip)) {
				response.getWriter().print("access forbit");
				return null;
			}			
			Response<Integer> resp = voteService.delCompany(companyId);
			return "redirect:/admin?categoryId="+resp.getBody();
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "admin/modify",method= RequestMethod.POST)
	public String modifyCompany(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "companyId", required = true) Integer companyId,
			@RequestParam(value = "categoryId", required = true) Integer categoryId,
			@RequestParam(value = "companyName", required = true) String companyName,
			@RequestParam(value = "voteNum", required = true) Integer voteNum,
			@RequestParam("companyImage") MultipartFile companyImage,
			ModelMap map) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if(!"202.85.220.36".equals(ip)) {
				response.getWriter().print("access forbit");
				return null;
			}		
			Response<Integer> resp = voteService.modifyCompany(companyId, companyName, categoryId, voteNum, companyImage, request.getRealPath("/"));
			return "redirect:/admin?categoryId="+categoryId;
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "admin/onLine",method= RequestMethod.GET)
	public String onLine(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "companyId", required = true) Integer companyId,
			ModelMap map) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if(!"202.85.220.36".equals(ip)) {
				response.getWriter().print("access forbit");
				return null;
			}		
			Response<Integer> resp = voteService.modifyCompanyStatus(companyId, 1);
			return "redirect:/admin?categoryId="+resp.getBody();
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
	
	@RequestMapping(value = "admin/offLine",method= RequestMethod.GET)
	public String offLine(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "companyId", required = true) Integer companyId,
			ModelMap map) {
		try {
			String ip = request.getHeader("X-Real-IP");
			if(!"202.85.220.36".equals(ip)) {
				response.getWriter().print("access forbit");
				return null;
			}
			Response<Integer> resp = voteService.modifyCompanyStatus(companyId, 0);
			return "redirect:/admin?categoryId="+resp.getBody();
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
			return null;
		}
	}
}
