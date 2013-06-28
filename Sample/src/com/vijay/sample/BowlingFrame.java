package com.vijay.sample;

import java.util.ArrayList;
import java.util.List;

public class BowlingFrame {
	
	private List<Integer> rolls = new ArrayList<Integer>();
	BowlingFrame previous;
	BowlingFrame next;
	boolean last;
	
	public BowlingFrame(BowlingFrame previous, int roll1, int roll2, int roll3, boolean last) {
		this(previous, roll1, roll2, last);
		this.roll(roll3);
	}
	public BowlingFrame(BowlingFrame previous, int roll1, int roll2, boolean last) {
		this(previous, roll1);
		this.last = last;
		this.roll(roll2);
	}
	
	public BowlingFrame(BowlingFrame previous, int roll1, int roll2) {
		this(previous, roll1);
		this.roll(roll2);
	}
	
	public BowlingFrame(BowlingFrame previous, int roll1) {
		super();
		if (previous != null && previous.isLast()) {
			throw new IllegalStateException("Game done... cannont add more frames");
		}
		if (previous != null ) {
			this.previous = previous;
			previous.next = this;
		}
		this.last = false;
		this.roll(roll1);
	}
	
	
	public void roll(int pins) {		
		checkValidRoll(pins);
		getRolls().add(pins);
	}

	public int getFrameScore() {
		int score = sum();
		if ((isSpare() || isStrike()) && !isLast()) {
			return score + calculate();
		} else return score;
	}

	private int calculate() {
		if (isLast()) {
			return 0;
		}
		checkValidNext();			
		if (isSpare())
			return getFirstRollOfNext();
		else {
			if (next.getRolls().size() == 2) {
				return next.sum();
			} else if (next.getRolls().size() == 3) {
				return next.getRolls().get(0) + next.getRolls().get(1);
			} else return next.sum() + next.getFirstRollOfNext();
		}
	}

	private boolean isLast() {
		return last;
	}

	private void checkValidNext() {
		if (next == null) {
			throw new IllegalStateException("Waiting for User to roll next Frame");
		}
	}
	
	private void checkValidRoll(int pins) {
		if (isLast()) {
			if (getRolls().get(0) != 10 && getRolls().size() == 2)
				throw new IllegalStateException("Illegal roll");
			else if (getRolls().get(0) == 10 && getRolls().size() == 3)
				throw new IllegalStateException("Illegal roll");
		} else if (getRolls().size() == 2 || sum() == 10 || pins > 10 || sum() + pins > 10) {
			throw new IllegalStateException("Illegal roll");
		}
	}

	private Integer getFirstRollOfNext() {		
		checkValidNext();
		return next.getRolls().get(0);
	}

	private boolean isStrike() {
		return sum() == 10 && getRolls().size() == 1;
	}

	private boolean isSpare() {
		return sum() == 10 && getRolls().size() == 2;
	}

	private int sum() {
		int score = 0;
		for (Integer roll : getRolls()) {
			score += roll;
		}
		return score;
	}

	public int getGameScore() {
		return getFrameScore() + ((previous != null)? previous.getGameScore(): 0);
	}

	public List<Integer> getRolls() {
		return rolls;
	}

	public void setRolls(List<Integer> rolls) {
		this.rolls = rolls;
	}

}
