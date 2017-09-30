package robbin;
public  class MachineConfig {

	private final String host;// 这里是ip:port结构
	private final Integer weight;// 权重
	public MachineConfig(String host, Integer weight) {
		super();
		this.host = host;
		this.weight = weight;
	}
	public String getHost() {
		return host;
	}
	public Integer getWeight() {
		return weight;
	}
	@Override
	public String toString() {
		return "MachineConfig [host=" + host + ", weight=" + weight + "]";
	}



}