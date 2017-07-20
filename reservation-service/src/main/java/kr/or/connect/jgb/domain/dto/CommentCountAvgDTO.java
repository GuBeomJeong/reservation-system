package kr.or.connect.jgb.domain.dto;

public class CommentCountAvgDTO {
	int count;
	double average;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		if(average == null) {
			this.average = 0;
		}else {
			this.average = Math.round(average * 100) / 100.0;
		}
	}
	

}
