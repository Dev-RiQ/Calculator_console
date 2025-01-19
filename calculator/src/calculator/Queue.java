package calculator;

import java.util.ArrayList;

public class Queue {
	private ArrayList<Double> num;
	private ArrayList<String> symbol;

	private Queue() {
		num = new ArrayList<>();
		symbol = new ArrayList<>();
	}

	protected ArrayList<Double> getNum() {
		return num;
	}

	protected ArrayList<String> getSymbol() {
		return symbol;
	}

	protected void setNum(ArrayList<Double> num) {
		this.num = num;
	}

	protected void setSymbol(ArrayList<String> symbol) {
		this.symbol = symbol;
	}

	private static Queue instance;

	public static Queue getInstance() {
		if (instance == null)
			instance = new Queue();
		return instance;
	}

	public void setNum(Double value) {
		num.add(value);
	}

	public void setSymbol(String value) {
		symbol.add(value);
	}

	public void removeNum(int idx) {
		for (int i = 0; i < 2; i++)
			num.remove(idx);
	}

	public void removeSymbol(int idx) {
		symbol.remove(idx);
	}

	public void setCalcNum(int idx, double num) {
		this.num.add(idx, num);
	}

	private String getString() {
		String str = " = ";
		if (num.get(0) % 1 == 0) {
			String temp = num.get(0) + "";
			str += temp.substring(0, temp.length() - 2);
		} else
			str += num.get(0) + "";
		return str;
	}

	@Override
	public String toString() {
		return getString();
	}
}
