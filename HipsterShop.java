public class HipsterShop {
	private String name;
	private String representImage;
	private String intro;
	private String address;
	private String longitude;
	private String latitude;
	private String openTime;
	private String phone;
	private String fax;
	private String email;
	private String facebook;
	private String website;
	private String headCityName;
	private String arriveWay;
	private String name_eng;
	private String intro_eng;
	private String openTime_eng;
	private String arriveWay_eng;
	private String mainTypeName;
	private String cityName;
	private String groupTypeName;
	private String mainTypePk;
	private String version;
	private int hitRate;
	
	public HipsterShop() {
	}
	public HipsterShop(String name, String representImage, String intro, String address, String longitude,
			String latitude, String openTime, String phone, String fax, String email, String facebook, String website,
			String arriveWay, String name_eng, String intro_eng, String openTime_eng, String arriveWay_eng,
			String mainTypeName, String cityName, String groupTypeName, String mainTypePk, String version,
			int hitRate) {
		super();
		this.name = name;
		this.representImage = representImage;
		this.intro = intro;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.openTime = openTime;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.facebook = facebook;
		this.website = website;
		this.arriveWay = arriveWay;
		this.name_eng = name_eng;
		this.intro_eng = intro_eng;
		this.openTime_eng = openTime_eng;
		this.arriveWay_eng = arriveWay_eng;
		this.mainTypeName = mainTypeName;
		this.cityName = cityName;
		this.groupTypeName = groupTypeName;
		this.mainTypePk = mainTypePk;
		this.version = version;
		this.hitRate = hitRate;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRepresentImage() {
		return representImage;
	}
	public void setRepresentImage(String representImage) {
		this.representImage = representImage;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getHeadCityName() {
		return headCityName;
	}
	public void setHeadCityName(String headCityName) {
		this.headCityName = headCityName;
	}
	public String getArriveWay() {
		return arriveWay;
	}
	public void setArriveWay(String arriveWay) {
		this.arriveWay = arriveWay;
	}
	public String getName_eng() {
		return name_eng;
	}
	public void setName_eng(String name_eng) {
		this.name_eng = name_eng;
	}
	public String getIntro_eng() {
		return intro_eng;
	}
	public void setIntro_eng(String intro_eng) {
		this.intro_eng = intro_eng;
	}
	public String getOpenTime_eng() {
		return openTime_eng;
	}
	public void setOpenTime_eng(String openTime_eng) {
		this.openTime_eng = openTime_eng;
	}
	public String getArriveWay_eng() {
		return arriveWay_eng;
	}
	public void setArriveWay_eng(String arriveWay_eng) {
		this.arriveWay_eng = arriveWay_eng;
	}
	public String getMainTypeName() {
		return mainTypeName;
	}
	public void setMainTypeName(String mainTypeName) {
		this.mainTypeName = mainTypeName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getGroupTypeName() {
		return groupTypeName;
	}
	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	}
	public String getMainTypePk() {
		return mainTypePk;
	}
	public void setMainTypePk(String mainTypePk) {
		this.mainTypePk = mainTypePk;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getHitRate() {
		return hitRate;
	}
	public void setHitRate(int hitRate) {
		this.hitRate = hitRate;
	}
	
	@Override
	public String toString() {
		/*return "HipsterShop [name=" + name + ", representImage=" + representImage + ", intro=" + intro + ", address="
				+ address + ", longitude=" + longitude + ", latitude=" + latitude + ", openTime=" + openTime
				+ ", phone=" + phone + ", fax=" + fax + ", email=" + email + ", facebook=" + facebook + ", website="
				+ website + ", arriveWay=" + arriveWay + ", name_eng=" + name_eng + ", intro_eng=" + intro_eng
				+ ", openTime_eng=" + openTime_eng + ", arriveWay_eng=" + arriveWay_eng + ", mainTypeName="
				+ mainTypeName + ", cityName=" + cityName + ", groupTypeName=" + groupTypeName + ", mainTypePk="
				+ mainTypePk + ", version=" + version + ", hitRate=" + hitRate + "]";*/
		String spliter = "\n\n";
		String pattern = "name: %s" + spliter;
		pattern += "representImage: %s" + spliter;
		pattern += "intro: %s" + spliter;
		pattern += "address: %s" + spliter;
		pattern += "longitude: %s" + spliter;
		pattern += "latitude: %s" + spliter;
		pattern += "openTime: %s" + spliter;
		pattern += "phone: %s" + spliter;
		pattern += "fax: %s" + spliter;
		pattern += "email: %s" + spliter;
		pattern += "facebook: %s" + spliter;
		pattern += "website: %s" + spliter;
		pattern += "arriveWay: %s" + spliter;
		pattern += "name_eng: %s" + spliter;
		pattern += "intro_eng: %s" + spliter;
		pattern += "openTime_eng: %s" + spliter;
		pattern += "arriveWay_eng: %s" + spliter;
		pattern += "mainTypeName: %s" + spliter;
		pattern += "cityName: %s" + spliter;
		pattern += "groupTypeName: %s" + spliter;
		pattern += "mainTypePk: %s" + spliter;
		pattern += "version: %s" + spliter;
		pattern += "hitRate: %d";
		
		return String.format(pattern, name, representImage, intro, address, longitude,
				latitude, openTime, phone, fax, email, facebook, website, arriveWay,
				name_eng, intro_eng, openTime_eng, arriveWay_eng, mainTypeName, cityName,
				groupTypeName, mainTypePk, version, hitRate);
	}
}
