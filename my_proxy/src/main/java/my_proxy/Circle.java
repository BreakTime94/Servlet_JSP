package my_proxy;

public class Circle {
	double r;

	public Circle(double r) {
		this.r = r;
	}
	
	Circle area() {
		System.out.println(3.14 * r * r);
		return this; // 자기자신 반환
	}
	
	Circle round() {
		System.out.println(2 * 3.14 * r);
		return this;
	}
	
	Circle setR(double r) {
		this.r = r;
		return this;
	}
}
