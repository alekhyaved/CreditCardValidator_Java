package com.CreditCardProblem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskRunnerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCsv() {
		TaskRunner taskRunner = new TaskRunner();
		File file = new File ("/Users/arnoleninmalyala/Desktop/combination.csv");
		String expected = "csv";
		String actual = taskRunner.getFileExtension(file);
		
		assertEquals(expected,actual);
		
	}
	
	@Test
	void testXml() {
		TaskRunner taskRunner = new TaskRunner();
		File file = new File ("/Users/arnoleninmalyala/Desktop/combinationData.xml");
		String expected = "xml";
		String actual = taskRunner.getFileExtension(file);
		
		assertEquals(expected,actual);
		
	}
	
	@Test
	void testJson() {
		TaskRunner taskRunner = new TaskRunner();
		File file = new File ("/Users/arnoleninmalyala/Desktop/combinationData.json");
		String expected = "json";
		String actual = taskRunner.getFileExtension(file);
		
		assertEquals(expected,actual);
		
	}
	

}
