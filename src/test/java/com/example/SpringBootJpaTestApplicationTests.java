package com.example;

import com.example.model.ItemEntity;
import com.example.model.OrderEntity;
import com.example.model.UserEntity;
import com.example.service.ItemService;
import com.example.service.OrderService;
import com.example.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootJpaTestApplication.class)
@Transactional
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
		System.out.println("testUserService()");

		Long userId1 = userService.join("Curry");
		Long userId2 = userService.join("James");

		UserEntity user1 = userService.findOne(userId1);
		UserEntity user2 = userService.findOne(userId2);

		System.out.println(String.format("user1: %s", user1.toString()));
		System.out.println(String.format("user2: %s", user2.toString()));
	}

	@Test
	public void testItemService() throws Exception {
		System.out.println("testItemService()");

		Long itemId1 = itemService.add("Book");
		Long itemId2 = itemService.add("TV");

		ItemEntity item1 = itemService.findOne(itemId1);
		ItemEntity item2 = itemService.findOne(itemId2);

		System.out.println(String.format("item1: %s", item1.toString()));
		System.out.println(String.format("item2: %s", item2.toString()));
	}

	@Test
	public void testOrderService() throws Exception {
		System.out.println("testOrderService()");

		Long userId1 = userService.join("Thompson");
		Long itemId1 = itemService.add("Ball");

		OrderEntity order1 = new OrderEntity(userId1, itemId1);
		order1 = orderService.order(order1);
		order1 = orderService.findOne(order1.getOrderEntityPk());

		System.out.println(String.format("order1: %s", order1.toString()));
	}

	@Test
	public void testOrderShowAll() throws Exception {
		System.out.println("testOrderShowAll()");

		Long userId1 = userService.join("Irving");
		Long itemId1 = itemService.add("Shoes");

		OrderEntity order1 = new OrderEntity(userId1, itemId1);
		order1 = orderService.order(order1);

		List<OrderEntity> orders = orderService.showAll();

		orders.stream().forEach(System.out::println);
	}

	@Test
	public void testOrderDelete() throws Exception {
		System.out.println("testOrderDelete()");

		Long userId1 = userService.join("Jefferson");
		Long itemId1 = itemService.add("Shirt");

		OrderEntity order1 = new OrderEntity(userId1, itemId1);

		doTransactionalAction(order1);

		OrderEntity order5 = orderService.findOne(order1.getOrderEntityPk());
		Assert.assertNotNull(order5);
	}

	@Transactional
	public void doTransactionalAction(OrderEntity order1) {
		orderService.order(order1);
		OrderEntity order2 = orderService.findOne(order1.getOrderEntityPk());
		Assert.assertNotNull(order2);

		orderService.delete(order1);
		OrderEntity order3 = orderService.findOne(order1.getOrderEntityPk());
		Assert.assertNull(order3);

		orderService.order(order1);
		OrderEntity order4 = orderService.findOne(order1.getOrderEntityPk());
		Assert.assertNotNull(order4);
	}
}
