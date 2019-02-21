package tk.mybatis.simple.type;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Enabled.values());
		for(Enabled enabled : Enabled.values()) {
			System.out.println(enabled.name());
			System.out.println(enabled.getValue());
			System.out.println(enabled.ordinal());
		}
	}

}
