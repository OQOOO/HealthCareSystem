package kr.ac.kopo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.UserVO;

@RestController
public class ApiController {
	
//	@Autowired
//	private NewsDAO dao;
//	
//	@Autowired
//	private NewsVO vo;
	
	@Autowired
	private UserDAO dao;
	
//	@RequestMapping("/newsApi")
//	public ResponseEntity<null> newsViewApi() {
//		
//		
//		return ResponseEntity.ok(null);
//	}
	
	@RequestMapping("/testApi")
	public List<String> testListApi() {
		
		List<String> li = new ArrayList<>();
		li.add("100");
		li.add("200");
		li.add("200");
		
		return li;
	}
	
	@RequestMapping("/pyLogin")
	public ResponseEntity<UserVO> pyLogin(@RequestBody UserVO vo) {
		
		System.out.println(vo.getUserId() + " : "  + vo.getPassword());
		
		UserVO reVo = new UserVO();
		
		Object logResult = dao.userCheck(vo);
		
		if(logResult != null) {
			reVo = (UserVO)logResult;
		}
		
		System.out.println(reVo);
		
		return ResponseEntity.ok(reVo);
	}
	
}