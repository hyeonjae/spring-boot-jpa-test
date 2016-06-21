package com.example;

import com.example.model.ItemEntity;
import com.example.model.OrderEntity;
import com.example.model.UserEntity;
import com.example.service.ItemService;
import com.example.service.OrderService;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootJpaTestApplication.class)
public class SpringBootJpaTestApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	@Autowired
	OrderService orderService;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testUserService() throws Exception {
		UserEntity user1 = new UserEntity("Curry");
		Long userId1 = userService.join(user1);

		UserEntity user2 = new UserEntity("James");
		Long userId2 = userService.join(user2);

		System.out.println(String.format("user1: %s", user1.toString()));
		System.out.println(String.format("user2: %s", user2.toString()));
	}

	@Test
	public void testItemService() throws Exception {
		ItemEntity item1 = new ItemEntity("Book");
		Long itemId1 = itemService.add(item1);

		ItemEntity item2 = new ItemEntity("TV");
		Long itemId2 = itemService.add(item2);

		System.out.println(String.format("item1: %s", item1.toString()));
		System.out.println(String.format("item2: %s", item2.toString()));
	}

	@Test
	public void testOrderService() throws Exception {
		UserEntity user1 = new UserEntity("Thompson");
		Long userId1 = userService.join(user1);

		ItemEntity item1 = new ItemEntity("Ball");
		Long itemId1 = itemService.add(item1);

		OrderEntity order1 = new OrderEntity(userId1, itemId1);
		order1 = orderService.order(order1);

		System.out.println(String.format("order1: %s", order1.toString()));
	}
}
