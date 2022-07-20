package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.service.UserService;
import com.example.vo.UserVo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
class UserTest {
	@Autowired
//	private ApplicationContext ctx;
	private UserService userService;
	
//	@Autowired
//	private UserService userService;
//	private DataSource dataSource;
	
//	@BeforeEach
//	public void init() {
//		this.ctx = new GenericXmlApplicationContext();
//		this.ctx.load("classpath:beans.xml");
//		this.ctx.refresh();
//	}
	
	
//	@Test
//	void test() {
//		assertNotNull(this.ctx);
//		assertNotNull(this.userService);
//		DataSource dataSource = (DataSource)this.ctx.getBean("dataSource");
//		assertNotNull(dataSource);
//	}
	
	@Test
	public void test1() {
		UserVo user = this.userService.selectUser("jimin");
		assertEquals("한지민", user.getName());
		System.out.println(user);
	}
	
	@Disabled @Test
	public void test2() {
		List<UserVo> list = this.userService.selectAllUsers();
		for(UserVo user : list) {
			System.out.println(user);
		}
	}
	
//	@Test
//	public void test2() {
//		UserVo user = this.userService.selectUser("chulsu");
//		assertEquals("김철수", user.getName());
//		System.out.println(user);
//	}
	
//	@Disabled @Test
//	public void test4() {
//		UserVo user = new UserVo("chulsu", "김철수", "남", "부산");
//		int rowCount = this.userService.insertUser(user);
//		assertEquals(1, rowCount);
//	}
	
//	@Disabled @Test
//	public void test4() {
//		UserVo user = new UserVo("younghee", "이영희", "여", "대구");
//		int rowCount = this.userService.insertUser(user);
//		assertEquals(1, rowCount);
//	}
	
//	@Test
//	public void test5() {
//		int rowCount = this.userService.deleteUser("younghee");
//		assertEquals(1, rowCount);
//	}
	
//	@Test
//	public void test3() {
//		UserVo user = new UserVo("chulsu", "박철수", "남", "광주");
//		int rowCount = this.userService.updateUser(user);
//		assertEquals(1, rowCount);
//	}
	
//	@AfterEach
//	public void destroy() {
//		this.ctx.close();
//	}
}
