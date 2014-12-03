package zx.soft.jetty.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import zx.soft.jetty.model.User;
import zx.soft.jetty.model.UserQueryCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/webapp/WEB-INF/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class UserMapperTest {

	final long uid_1 = 1;
	final long uid_2 = 2;
	final String user_101 = "User:{uid=1, mid=100, name=张三, nick=张三昵称, gender=0}";
	final String user_102 = "User:{uid=1, mid=101, name=张三, nick=张三昵称, gender=1}";
	final String user_103 = "User:{uid=2, mid=100, name=李四, nick=李四昵称, gender=0}";
	final String user_104 = "User:{uid=2, mid=101, name=李四, nick=李四昵称, gender=1}";

	@Inject
	private UserMapper userMapper;

	@Test
	public void testAdd() {
		User user = new User().setUid(200).defaultValue();
		userMapper.add(user);
		assertEquals(user.toString(), userMapper.get(200, user.getMid()).toString());

		user = new User().setUid(200).setName("赵六").setNick("赵六昵称").setGender(2);
		userMapper.add(user);
		assertEquals(user.toString(), userMapper.get(200, user.getMid()).toString());
		System.err.println(userMapper.get(200, user.getMid()).toString());
	}

	public void testGetUser() {
		User user = userMapper.get(uid_1, 101);
		assertEquals(user_102, user.toString());
		assertNull(userMapper.get(uid_1, 12345678)); // 没有该用户
	}

	public void testGetUsers() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid_1);
		List<User> users = userMapper.list(condition);

		assertEquals(2, users.size());
		assertEquals(user_101, users.get(0).toString());
	}

	public void testGetUsers_gender() {
		UserQueryCondition condition = new UserQueryCondition().setUid(uid_1).setGender(1);
		List<User> users = userMapper.list(condition);
		assertEquals(1, users.size());
		assertEquals(user_102, users.get(0).toString());
	}

	public void testQueryCountByUid() {
		int count = userMapper.queryCountByUid(uid_1);
		assertEquals(count, 2);
	}

	public void testUpdate() {
		User user = userMapper.get(uid_2, 100);
		user.setName("张三更新").setGender(1);
		userMapper.update(user);
		assertEquals("User:{uid=2, mid=100, name=李四, nick=张三更新, gender=0}", userMapper.get(uid_2, 100).toString());

		user.setName(""); // 抹掉名字
		userMapper.update(user);
		assertEquals("User:{uid=2, mid=100, name=, nick=张三更新, gender=0}", userMapper.get(uid_2, 100).toString());
	}

	public void toJson() {
		User user = new User().setUid(1).setMid(1).setName("zhangsan").setGender(1);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(user));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
