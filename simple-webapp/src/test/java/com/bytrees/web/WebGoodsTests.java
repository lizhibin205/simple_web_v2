package com.bytrees.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebGoodsTests {
	@Autowired
	private WebApplicationContext webContext;
	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void goodsAdd() throws Exception {
		//run set and get
		//mockMvc.perform(post("/goods/add")
		//		.param("name", "goodsTest"))
		//.andExpect(status().is2xxSuccessful())
		//.andExpect(content().string("ok, id=1"));
		
		//mockMvc.perform(get("/goods/1"))
		//.andExpect(content().string("goodsTest"));
	}
}
