package test.demo1;

import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zsoft.entity.User;

public class TestBean {
	@Test
	public void test1(){
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springmvc3-servlet.xml");
		User user = ctx.getBean("user",User.class);
		System.out.println(user);
		String uid = UUID.randomUUID().toString();
		user.setUserName("licc");
		user.setUserId(uid);
		user.setPwd("123456");
		user.setLoc("zhangsanlisi");
		System.out.println(user);
		
	}
}
