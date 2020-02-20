package test.controller;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class WebTestAddFile {

	@Test
	public void testAddFileSelenium() {
		assertEquals("prova2", SeleniumAddFile.addFileTestSelenium());
	}
}
