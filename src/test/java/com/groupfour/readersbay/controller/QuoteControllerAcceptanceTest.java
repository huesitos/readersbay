package com.groupfour.readersbay.controller;

import com.google.gson.Gson;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerAcceptanceTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void fetchBookQuotes() {
    try {
      MvcResult mvcResult = mockMvc.perform(get("/books/1/quotes")
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk()).andReturn();

      assertEquals(0,
          new Gson()
              .fromJson(mvcResult.getResponse().getContentAsString(), JSONArray.class)
              .size());
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}