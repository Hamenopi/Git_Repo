package com.hamenopi.thecheese.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hamenopi.thecheese.graphics.Screen;

public class MathEntity extends Entity {
	protected double xa, ya;
	protected String name;
	private int operandA;
	private int operandB;
	private int result;
	
	private MathEntity results[] = new MathEntity[3];
	private String selection[] = new String[3];
	private List<MathEntity> mes = new ArrayList<>();
	
	public MathEntity() {
		id = EntityIDList.MATHENTITY;
	}
	
	public MathEntity(int val) {
		this();
		name = Integer.toString(val);
	}
	
	public MathEntity(String type, int scale) {
		this();
		if (type == "ADD") {
			buildAddition(scale);
			
		} else {
			buildSubtraction(scale);
		}
		setAnswers();
		shuffleAnswers();
	}
	
	private void buildSubtraction(int scale) {
		scale = setScale(scale);
		operandA = random.nextInt(scale);
		operandB = random.nextInt(scale);
		if (operandA > operandB) {
			result = operandA - operandB;
			
		} else {
			result = operandB - operandA;
		}
		name = createEq("SUB", operandA, operandB);
	}
	
	private void buildAddition(int scale) {
		scale = setScale(scale);
		operandA = random.nextInt(scale);
		operandB = random.nextInt(scale);
		result = operandA + operandB;
		name = createEq("ADD", operandA, operandB);
	}
	
	private void shuffleAnswers() {
		for (int i = 0; i < results.length; i++) {
			mes.add(results[i]);
		}
		
		Collections.shuffle(mes);
		
		for (int i = 0; i < mes.size(); i++) {
			selection[i] = mes.get(i).name;
		}
	}
	
	private int setScale(int scale) {
		if (scale > 1) scale = 1;
		return scale *= 10;
	}
	
	private void setAnswers() {
		results[0] = new MathEntity(result);
		results[1] = new MathEntity(wrongAnswer(result));
		results[2] = new MathEntity(wrongAnswer(result));
	}
	
	private String createEq(String type, int operandA, int operandB) {
		String sA = Integer.toString(operandA);
		String sB = Integer.toString(operandB);
		if (type == "ADD") return (sA + " + " + sB + " = ? ");
		if (type == "SUB" && operandA >= operandB) return (sA + " - " + sB + " = ? ");
		if (type == "SUB" && operandA <= operandB) return (sB + " - " + sA + " = ? ");
		return "";
	}
	
	private int wrongAnswer(int answer) {
		int result;
		while (true) {
			result = answer + random.nextInt(20) - 10;
			if (result != answer) return result;
		}
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		if (results[0] != null) screen.uirender(name + "\nA:" + selection[0] + "\nB:" + selection[1] + "\nC:" + selection[2]);
	}
}
