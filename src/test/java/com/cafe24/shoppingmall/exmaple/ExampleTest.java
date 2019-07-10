package com.cafe24.shoppingmall.exmaple;

import static org.junit.Assert.*;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class ExampleTest {
	
	//Sync ... 
	//테스트 케이스(메소드)끼리 공유해야 할 변수가 있으면
	// Static~! 
	private static StringBuilder output = new StringBuilder();
	
	
	//Thread로 만들어서 돌리므로 테스트 시작 전, 후 로 하는 애들은 static으로 선언해줘야 한다.
	@BeforeClass
	public static void setUpBefore() {
		System.out.println("@BeforeClass");
	}
	
	@AfterClass
	public static void tearDownAfter() {
		System.out.println("@AfterClass : " + output.toString());
		
	}
	
	@Before
	public void setUp() {
		System.out.println("@Before");
	}
	
	
	@After
	public void tearDown() {
		System.out.println("@After");
	}
	
	
	@Test
	public void myATest() {
		System.out.println("@Test:A");
		output.append("A");
	}
	
	
	@Test
	public void myBTest() {
		System.out.println("@Test:B");
		output.append("B");
	}
	
	@Test
	public void myCTest() {
		System.out.println("@Test:C");
		output.append("C");
	}
	
	
	
	/* ===== assertXYZ 테스트  ===== */
	
	@Ignore
	@Test
	public void ignoreTest() {
		assertTrue(false);
	}
	
	@Test
	public void testAssert() {
		assertTrue(true);
		assertFalse(false);
		
		
		assertNull(null);
		assertNotNull(new Object());
		
		
		assertEquals(1+2, 3);
		assertEquals(new String("hello"), "hello");
		assertNotEquals(true, false);
		
		
		assertSame("Hello", "Hello");
		assertNotSame(new String("Hello"), new String("Hello"));
		
		
		
		// assertThat : is
		assertThat(1+2 , is(3));
		assertThat("this is never", is(not("that")));
		
		// assertThat : allOf
		assertThat("Hello World", allOf(startsWith("Hell"), containsString("or")));
		
		// assertThat : anyOf
		assertThat("Hello World", anyOf(startsWith("Hello"), containsString("or")));
		
		// assertThat : both
		assertThat("ABC", both(containsString("A")).and(containsString("C")));
		
		// assertThat : either
		assertThat("ABC", either(containsString("A")).or(containsString("c")));		

		// assertThat : everyItem
		assertThat(Arrays.asList("Apple", "April", "Application", "Apolo"), everyItem(startsWith("Ap")));

		// assertThat : hasItem		
		assertThat(Arrays.asList("Apple", "Banana", "Red", "Black"), hasItem(startsWith("Ap")));

		
		
		// fails 에러 내기 
		//fail("All Over!!!!!!!");
		
	}
	
	
	@Test
	public void testAssert1() {
		Object[] a = {"Java", "JUnit", "Spring"};
		Object[] b = {"Java", "JUnit", "Spring"};
		
		assertArrayEquals(a, b);
	}
	
	@Test
	public void testAssert2() {
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
