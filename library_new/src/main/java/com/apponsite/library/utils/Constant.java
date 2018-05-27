package com.apponsite.library.utils;

import com.apponsite.library.camera.AspectRatio;

/**
 * Created by zhjh.
 */
public class Constant {

	public static final String LOG_TAG = " LOG";

	public final static String islogin = "login";
	public final static String imgurl = "imgUrl";

	public final static String isfirst = "first";

	public final static String utoken = "token";

	public final static String uuid = "uuid";

	public final static String realname = "realname";

	public final static String phone = "phone";

	public final static String uname = "uname";

	//维修班长
	public final static String team = "team";


	public final static String team_uuid = "team_uuid";

	public final static String roleuuid = "role_uuid";

	public final static String output = "&output=json&pois=1";

	public final static String geocoding = "http://api.map.baidu.com/geocoder/v2/?ak=bvFNUZkQ2joOwoMOMqjxt24CFhmwRvNp&location=";


	/**
	 * camera 包下
	 */
	public final static AspectRatio DEFAULT_ASPECT_RATIO = AspectRatio.of(4, 3);

	public final static int FACING_BACK = 0;
	public final static int FACING_FRONT = 1;

	public final static int FLASH_OFF = 0;
	public final static int FLASH_ON = 1;
	public final static int FLASH_TORCH = 2;
	public final static int FLASH_AUTO = 3;
	public final static int FLASH_RED_EYE = 4;

	public final static int LANDSCAPE_90 = 90;
	public final static int LANDSCAPE_270 = 270;
}
