package tf.endmodel;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;

public class endchart {
	private int sum;
	private int avg;
	
	
	
	public endchart() {
		super();
	}

	public endchart(int sum, int avg) {
		super();
		this.sum = sum;
		this.avg = avg;
	}




	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}
	
}
