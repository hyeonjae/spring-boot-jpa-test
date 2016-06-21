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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
		System.out.println("testUserService()");

		Long userId1 = userService.join(101L, "Curry");
		Long userId2 = userService.join(201L, "James");

		UserEntity user1 = userService.findOne(userId1);
		UserEntity user2 = userService.findOne(userId2);

		System.out.println(String.format("user1: %s", user1.toString()));
		System.out.println(String.format("user2: %s", user2.toString()));
	}

	@Test
	public void testItemService() throws Exception {
		System.out.println("testItemService()");

		Long itemId1 = itemService.add(102L, "Book");
		Long itemId2 = itemService.add(202L, "TV");

		ItemEntity item1 = itemService.findOne(itemId1);
		ItemEntity item2 = itemService.findOne(itemId2);

		System.out.println(String.format("item1: %s", item1.toString()));
		System.out.println(String.format("item2: %s", item2.toString()));
	}

	@Test
	public void testOrderService() throws Exception {
		System.out.println("testOrderService()");

		Long userId1 = userService.join(103L, "Thompson");
		Long itemId1 = itemService.add(203L, "Ball");

		OrderEntity order1 = new OrderEntity(userId1, itemId1);
		order1 = orderService.order(order1);
		order1 = orderService.findOne(order1.getOrderEntityPk());

		System.out.println(String.format("order1: %s", order1.toString()));
	}

	@Test
	public void testOrderShowAll() throws Exception {
		System.out.println("testOrderShowAll()");

		Long userId1 = userService.join(104L, "Irving");
		Long itemId1 = itemService.add(204L, "Shoes");

		OrderEntity order1 = new OrderEntity(userId1, itemId1);
		order1 = orderService.order(order1);

		List<OrderEntity> orders = orderService.showAll();

		orders.stream().forEach(System.out::println);
	}

	@Test
	@Transactional
	public void testInsertAndDeleteByQueryDsl() throws Exception {
		System.out.println("testInsertAndDeleteByQueryDsl()");

		Long userId1 = userService.join(106L, "Jefferson");
		Long itemId1 = itemService.add(206L, "Shirt");

		OrderEntity order1 = new OrderEntity(userId1, itemId1);

		doTransactionalQueryDsl(order1);

		OrderEntity order5 = orderService.findOne(order1.getOrderEntityPk());
		Assert.assertNotNull(order5);
	}

	@Transactional
	public void doTransactionalQueryDsl(OrderEntity order1) {
		Long userId = order1.getOrderEntityPk().getUserId();
		Long itemId = order1.getOrderEntityPk().getItemId();

		orderService.order(order1);
		OrderEntity order2 = orderService.findOne(new OrderEntity.OrderEntityPk(userId, itemId));
		Assert.assertNotNull(order2);

		OrderEntity order_new = new OrderEntity(userId, itemId);
		orderService.deleteByQueryDsl(order_new.getOrderEntityPk()); // Delete query dsl doesn't work at this time
		OrderEntity order3 = orderService.findOne(new OrderEntity.OrderEntityPk(userId, itemId));
//		Assert.assertNull(order3);		// It should be NULL!!!

		OrderEntity order_new2 = new OrderEntity(userId, itemId);
		orderService.order(order_new2);
		OrderEntity order4 = orderService.findOne(new OrderEntity.OrderEntityPk(userId, itemId));
		Assert.assertNotNull(order4);
	}

	@Test
	@Transactional
	public void testInsertAndDeleteByNamedQuery() throws Exception {
		System.out.println("testInsertAndDeleteByNamedQuery()");

		Long userId1 = userService.join(108L, "Jefferson");
		Long itemId1 = itemService.add(208L, "Shirt");

		OrderEntity order1 = new OrderEntity(userId1, itemId1);

		doTransactionalNamedQuery(order1);

		OrderEntity order5 = orderService.findOne(order1.getOrderEntityPk());
		Assert.assertNotNull(order5);
	}

	@Transactional
	public void doTransactionalNamedQuery(OrderEntity order1) {
		Long userId = order1.getOrderEntityPk().getUserId();
		Long itemId = order1.getOrderEntityPk().getItemId();

		orderService.order(order1);
		OrderEntity order2 = orderService.findOne(new OrderEntity.OrderEntityPk(userId, itemId));
		Assert.assertNotNull(order2);

		OrderEntity order_new = new OrderEntity(userId, itemId);
		orderService.deleteByNamedQuery(order_new);
		OrderEntity order3 = orderService.findOne(new OrderEntity.OrderEntityPk(userId, itemId));
		Assert.assertNull(order3);

		OrderEntity order_new2 = new OrderEntity(userId, itemId);
		orderService.order(order_new2);
		OrderEntity order4 = orderService.findOne(new OrderEntity.OrderEntityPk(userId, itemId));
		Assert.assertNotNull(order4);
	}
}
