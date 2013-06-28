package com.vijay.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingFrameTest {

	@Test
	public void test() {
		BowlingFrame frame1 = new BowlingFrame(null, 7, 0);
		
		assertEquals(7, frame1.getFrameScore());
		assertEquals(7, frame1.getGameScore());
		
		BowlingFrame frame2 = new BowlingFrame(frame1, 6, 4);
		try {
			frame2.getFrameScore();
			fail("not expected");
		}catch (IllegalStateException e){
			assertEquals("Waiting for User to roll next Frame", e.getMessage());
		}
		BowlingFrame frame3 = new BowlingFrame(frame2, 6, 3);
		
		assertEquals(16, frame2.getFrameScore());
		assertEquals(23, frame2.getGameScore());
		
		assertEquals(9, frame3.getFrameScore());
		assertEquals(32, frame3.getGameScore());
		
		BowlingFrame frame4 = new BowlingFrame(frame3, 10);
		try {
			frame4.getFrameScore();
			fail("not expected");
		}catch (IllegalStateException e){
			assertEquals("Waiting for User to roll next Frame", e.getMessage());
		}
		
		BowlingFrame frame5 = new BowlingFrame(frame4, 4, 5);
		
		assertEquals(19, frame4.getFrameScore());
		assertEquals(51, frame4.getGameScore());
		
		assertEquals(9, frame5.getFrameScore());
		assertEquals(60, frame5.getGameScore());
		
		BowlingFrame frame6 = new BowlingFrame(frame5, 10);
		BowlingFrame frame7 = new BowlingFrame(frame6, 10);
		BowlingFrame frame8 = new BowlingFrame(frame7, 10);
		
		assertEquals(30, frame6.getFrameScore());
		assertEquals(90, frame6.getGameScore());
		
		BowlingFrame frame9 = new BowlingFrame(frame8, 8,0);
		
		assertEquals(28, frame7.getFrameScore());
		assertEquals(118, frame7.getGameScore());
		
		assertEquals(18, frame8.getFrameScore());
		assertEquals(136, frame8.getGameScore());
		
		assertEquals(8, frame9.getFrameScore());
		assertEquals(144, frame9.getGameScore());
		
		BowlingFrame frame10 = new BowlingFrame(frame9, 10, 10, 10, true);
		
		assertEquals(30, frame10.getFrameScore());
		assertEquals(174, frame10.getGameScore());
		
		try {
			new BowlingFrame(frame10, 10);
		} catch (IllegalStateException e) {
			assertEquals("Game done... cannont add more frames", e.getMessage());
		}
		
	}
	
	@Test
	public void testFullScore() {
		BowlingFrame frame1 = new BowlingFrame(null, 10);
		
		BowlingFrame frame2 = new BowlingFrame(frame1, 10);
		BowlingFrame frame3 = new BowlingFrame(frame2, 10);
		BowlingFrame frame4 = new BowlingFrame(frame3, 10);
		BowlingFrame frame5 = new BowlingFrame(frame4, 10);
		BowlingFrame frame6 = new BowlingFrame(frame5, 10);
		BowlingFrame frame7 = new BowlingFrame(frame6, 10);
		BowlingFrame frame8 = new BowlingFrame(frame7, 10);
		BowlingFrame frame9 = new BowlingFrame(frame8, 10);
		BowlingFrame frame10 = new BowlingFrame(frame9, 10, 10, 10, true);
		
		assertEquals(300, frame10.getGameScore());
	}
}
