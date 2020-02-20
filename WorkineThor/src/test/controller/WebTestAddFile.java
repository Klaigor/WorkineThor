package test.controller;


import static org.junit.Assert.*;

import org.junit.Test;

public class WebTestAddFile {

	@Test
	public void testAddFileSelenium() {
		assertEquals("prova2", SeleniumAddFile.addFileTestSelenium());
	}
}
