package kr.ac.kopo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.kopo.dao.ExerciseDAO;
import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.vo.ExerciseVO;
import kr.ac.kopo.vo.UserVO;

@RestController
public class ApiController {
	
	@Autowired
	private UserDAO dao;
	
	@Autowired
	private ExerciseDAO edao;
	
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
	
	@RequestMapping("/pyAppMapping")
	public Map<String, String> webIp() {
		
		Map<String, String> apiMapping = new HashMap<String, String>();

		String host = "http://localhost:8088";
		
		apiMapping.put("login", host+"/pyLogin?");
		apiMapping.put("dumbbellEnd", host+"/exercisePy?");
		apiMapping.put("pushupEnd", host+"/exercisePy?");
		apiMapping.put("fingerCnt", host+"/fingerCounterPy");
		
		return apiMapping;

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
	
	@RequestMapping("/fingerCounterPy")
	public String fingerCount(@RequestBody ExerciseVO vo, Model model) {
		System.out.println("fingerCount: " + vo.getCnt());
		
		return "Index";
	}
	
	@RequestMapping("/exercisePy")
	public String getDataTest(@RequestBody ExerciseVO apiVo, Model model) {
		System.out.println("IndexController: " + apiVo);
		edao.insertExerciseData(apiVo);
		
		return "Index";
	}

}




