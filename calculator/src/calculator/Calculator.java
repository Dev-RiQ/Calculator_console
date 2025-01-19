package calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

	private Function function;
	private Queue queue;
	private String msg;
	private boolean isEnd;

	private Calculator() {
		function = Function.getInstance();
		queue = Queue.getInstance();
		run();
	}

	private static Calculator instance;

	public static Calculator getInstance() {
		if (instance == null)
			instance = new Calculator();
		return instance;
	}

	private Double getValue(Scanner sc, String msg) {
		while (true) {
			System.out.print(msg + " 입력 >> ");
			try {
				return sc.nextDouble();
			} catch (Exception e) {
				System.out.println("입력 오류");
			} finally {
				sc.nextLine();
			}
		}
	}

	private Double getValue(Scanner sc, String msg, int start, int end) {
		while (true) {
			Double sel = getValue(sc, msg);
			if (!(sel < start || sel > end))
				return sel;
			System.out.println("입력 오류");
		}
	}

	private String getSymbol(Double sel) {
		String symbol = "";
		switch (Integer.parseInt(sel.toString().substring(0, 1))) {
		case 1 : symbol = "+"; break;
		case 2 : symbol = "-"; break;
		case 3 : symbol = "/"; break;
		case 4 : symbol = "*"; break;
		}
		queue.setSymbol(symbol);
		return symbol;
	}

	public void calc(ArrayList<Double> num, ArrayList<String> symbol) {
		while (num.size() > 1) {
			int idx = priority(symbol);
			try {
				calcFunction(getSymbol(symbol, idx), getNums(num, idx), idx);
			} catch (Exception e) {
				System.out.println("0으로 나눌 수 없습니다.");
				return;
			}
		}
	}

	private void calcFunction(String symbol, double[] nums, int idx) throws Exception {
		double num = 0;
		if (symbol.equals("+")) {
			num = function.plus(nums);
		} else if (symbol.equals("-")) {
			num = function.minus(nums);
		} else if (symbol.equals("/")) {
			num = function.divide(nums);
		} else if (symbol.equals("*")) {
			num = function.multiple(nums);
		}
		queue.setCalcNum(idx, num);
	}

	private double[] getNums(ArrayList<Double> num, int idx) {
		double[] nums = { num.get(idx), num.get(idx + 1) };
		queue.removeNum(idx);
		return nums;
	}

	private String getSymbol(ArrayList<String> symbol, int idx) {
		String sym = symbol.get(idx);
		queue.removeSymbol(idx);
		return sym;
	}

	private int priority(ArrayList<String> symbol) {
		for (int i = 0; i < symbol.size(); i++)
			if (symbol.get(i).equals("*") || symbol.get(i).equals("/"))
				return i;
		return 0;
	}

	private void inputNum(Double value) {
		queue.setNum(value);
		msg += value + " ";
		Screen.getInstance().show(msg);
	}

	private void inputSymbol(Double value) {
		if (value == 0) {
			isEnd = true;
			return;
		}
		msg += getSymbol(value) + " ";
		Screen.getInstance().show(msg);
	}

	private void showResult() {
		calc(queue.getNum(), queue.getSymbol());
		System.out.println(msg + "\n" + queue);
	}

	private void run() {
		Scanner sc = new Scanner(System.in);
		msg = "";
		while (!isEnd) {
			inputNum(getValue(sc, "숫자"));
			inputSymbol(getValue(sc, "(1) +   (2) -   (3) /   (4) *   (0) = \n기호", 0, 4));
		}
		showResult();
		sc.close();
	}
}
