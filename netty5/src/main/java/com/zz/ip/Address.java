package com.zz.ip;

public class Address {

	private String country;

	private String country_id;

	private String area;

	private Long area_id, region_id, city_id, county_id, isp_id;
	private String region, city, county, isp, ip;

	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("[country=");
		sb.append(this.country);
		sb.append(",country_id=");
		sb.append(this.country_id);
		sb.append(",area=");
		sb.append(this.area);
		sb.append(",area_id=");
		sb.append(this.area_id);
		sb.append(",region=");
		sb.append(this.region);
		sb.append(",region_id=");
		sb.append(this.region_id);
		sb.append(",city=");
		sb.append(this.city);
		sb.append(",city_id=");
		sb.append(city_id);
		sb.append(",county=");
		sb.append(this.county);
		sb.append(",county_id=");
		sb.append(this.county_id);
		sb.append(",isp=");
		sb.append(isp);
		sb.append(",isp_id=");
		sb.append(this.isp_id);
		sb.append(",ip=");
		sb.append(ip);
		sb.append("]");
		return sb.toString();
		
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getArea_id() {
		return area_id;
	}

	public void setArea_id(Long area_id) {
		this.area_id = area_id;
	}

	public Long getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Long region_id) {
		this.region_id = region_id;
	}

	public Long getCity_id() {
		return city_id;
	}

	public void setCity_id(Long city_id) {
		this.city_id = city_id;
	}

	public Long getCounty_id() {
		return county_id;
	}

	public void setCounty_id(Long county_id) {
		this.county_id = county_id;
	}

	public Long getIsp_id() {
		return isp_id;
	}

	public void setIsp_id(Long isp_id) {
		this.isp_id = isp_id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
