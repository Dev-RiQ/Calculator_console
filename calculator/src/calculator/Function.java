package calculator;

public class Function {
	private Function() {}
	private static Function instance;
	public static Function getInstance() {
		if(instance == null) instance = new Function();
		return instance;
	}

	public double plus(double[] nums) {
		return nums[0] + nums[1];
	}
	public double minus(double[] nums) {
		return nums[0] - nums[1];
	}
	public double divide(double[] nums) throws Exception {
		return nums[0] / nums[1];
	}
	public double multiple(double[] nums) {
		return nums[0] * nums[1];
	}
}
