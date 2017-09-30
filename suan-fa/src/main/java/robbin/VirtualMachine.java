package robbin;

public class VirtualMachine {
	private final String host;// 这里是ip:port结构
	private final Integer weight;// 权重
	private final Integer gwd;
	private final Integer quotient;

	public VirtualMachine(String host, Integer weight, Integer gwd) {
		super();
		this.host = host;
		this.weight = weight;
		this.gwd = gwd;
		this.quotient = weight / gwd;
	}

	public String getHost() {
		return host;
	}

	public Integer getQuotient() {
		return quotient;
	}

	@Override
	public String toString() {
		return "VirtualMachine [host=" + host + ", weight=" + weight + ", gwd=" + gwd + ", quotient=" + quotient + "]";
	}

	
	
	
}
