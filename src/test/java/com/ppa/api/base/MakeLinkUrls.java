package com.ppa.api.base;

public interface MakeLinkUrls {
	
	//Pre-requisites:
	public static final String AMAZON_NEW_PRODUCT_CASHABACK_URL 				= "{PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}";
	public static final String FLIPKART_PRODUCT_CASHBACK_URL					= "{PRDURL}&affid=rohanpouri&affExtParam1={EXTID}&aid={AFFLTID}";

	//SET : 1
	//This URL is having double questions mark  : Expected that it is a wrong URL format
	public static final String DOUBLEQUESTIONMARK_INVALID_URL 				= "https://www.jabong.com/g/th1bw6vg5q45926b5c5a8316cf038a/?id=19&shareid=LCENKR2812?id=20";
	
	//SET : 2
	//Having multiple URLS -  with only one valid domain URL - Expected that shorten URL should get generated
	public static final String HAVING_MULTIPLE_URLS_WITH_ONLY_ONE_VALID_DOMAIN		= "https://jabong.go2affise.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-";
	public static final String HAVING_MULTIPLE_URLS_WITH_ONLY_ONE_VALID_DOMAIN_SELLER	= "https://www.jabong.com/roadster-maroon-striped-polo-collar-tshirt-";

	
	//Amazon Cashback end seller URL format set in the admin is :  {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}
	//SET : 4 - its for Amazon
	public static final String VALIDPRODUCTURL 				= "https://www.amazon.in/gp/product/B00KAUFMQC/ref=s9_acsd_al_bw_c_x_5_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-5&pf_rd_r=M7HJTAWSWTRT4EGVC8CX&pf_rd_t=101&pf_rd_p=d16aaf23-0405-4b0c-83b2-c37c0c9137ad&pf_rd_i=6802110031";
	public static final String VALIDPRODUCTURL_SELLER 		= "https://www.amazon.in/gp/product/B00KAUFMQC/ref=s9_acsd_al_bw_c_x_5_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-5&pf_rd_r=M7HJTAWSWTRT4EGVC8CX&pf_rd_t=101&pf_rd_p=d16aaf23-0405-4b0c-83b2-c37c0c9137ad&pf_rd_i=6802110031&tag=earnkaro-21&ascsubtag=";
	public static final String VALIDPRODUCTURLWITHTAGS		= "https://www.amazon.in/gp/product/B00KAUFMQC/ref=s9_acsd_al_bw_c_x_5_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-5&pf_rd_r=M7HJTAWSWTRT4EGVC8CX&pf_rd_t=101&pf_rd_p=d16aaf23-0405-4b0c-83b2-c37c0c9137ad&pf_rd_i=6802110031&tag=coupo07-21&ascsubtag=CU3265d8c9d45cd1";
	
	//SET : 5
	//VALID URLS WITH TAGS OF COUPON DUNIA - IN SHORTEN URL
	public static final String VALIDSHORTENURLWITHTAGS_BITLY 		= "https://amzn.to/2UCs5mf";
	public static final String VALIDSHORTENURLWITHTAGS_BITDO 		= "http://bit.do/ePp7p";
	public static final String VALIDSHORTENURLWITHTAGS_TINYCC 		= "http://tiny.cc/xwm74y";
	public static final String VALIDSHORTENURLWITHTAGS_TINYURL 		= "http://tinyurl.com/y2jn3m48";
	
	// Flipkart Cashback end seller URL format set in the admin is :  {PRDURL}&affid=rohanpouri&affExtParam1={EXTID}&aid={AFFLTID}
	//SET : 6 - its for flipkart
	//VALID FLIPKART URL WITH TAGS OF COUPON DUNIA - IN LONG URL
	public static final String FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG 			  =  "https://www.flipkart.com/?affid=contact&affExtParam1=CU79a9ea080f05ba";
	public static final String FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG_AFTERREPLACED =  "https://www.flipkart.com/?affid=contact&affid=rohanpouri&affExtParam1=";
	public static final String FLIPKARTMAKELINK			  =  "https://www.flipkart.com/lg-6-2-kg-inverter-fully-automatic-top-load-silver-white/p/itmewv2u8wddak9h?pid=WMNEWV2UQWXURHJQ&srno=b_1_3&otracker=hp_bannerads_4_deskt-homep-3bcff_Whirlpool_70N0DC8LWDVG&lid=LSTWMNEWV2UQWXURHJQ1C8VIZ&fm=organic&iid=e8d169f4-d3f3-4f04-a1a8-5dc3f28a4d0a.WMNEWV2UQWXURHJQ.SEARCH&ppt=Homepage&ppn=Homepage&ssid=vn0tor8yts0000001555309381050";
	public static final String FLIPKARTMAKELINK_SELLER 	  = "https://www.flipkart.com/lg-6-2-kg-inverter-fully-automatic-top-load-silver-white/p/itmewv2u8wddak9h?pid=WMNEWV2UQWXURHJQ&srno=b_1_3&otracker=hp_bannerads_4_deskt-homep-3bcff_Whirlpool_70N0DC8LWDVG&lid=LSTWMNEWV2UQWXURHJQ1C8VIZ&fm=organic&iid=e8d169f4-d3f3-4f04-a1a8-5dc3f28a4d0a.WMNEWV2UQWXURHJQ.SEARCH&ppt=Homepage&ppn=Homepage&ssid=vn0tor8yts0000001555309381050&affid=rohanpouri&affExtParam1=";


	//SET : 3
	//Having multiple same domain URL : Should not allow to get the Brandly
	public static final String HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN = "https://amazon.in/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Famazon.in";
	public static final String HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN_BITLY = "https://amzn.to/2GnFnJc";
	
	
	//MYNTRA
	public static final String MYNTRAVALIDURL = "https://www.myntra.com/bedsheets/storyhome/storyhome-teal-blue--white-flat-120-tc-cotton-1-queen-bedsheet-with-2-pillow-covers/6786809/buy";
	public static final String MYNTRAVALIDURL_BITLY = "https://bit.ly/2VOrIRU";

	//MYNTRA MULTIPLE URL WITH ONLY ONE VALID URL :
	public static final String MYNTRAMULTIPLEURLWITHONLYONEVALID = "https://jabong.go2affise.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.myntra.com%2Froadster-maroon-striped-polo-collar-tshirt-";
	public static final String MYNTRAMULTIPLEURLWITHONLYONEVALID_BILTY = "https://bit.ly/2KHSJph";

	//JABONG :
	public static final String JABONGVALIDURL = "https://www.jabong.com/roadster-navy-blue-colourblocked-regular-fit-polo-t-shirt-4828564.htm?pos=2";
	public static final String JABONGVALIDURL_BITLY = "https://bit.ly/2VOwRtg";
	
	//JABONG MULTIPLE URL WITH ONLY ONE VALID URL :
	public static final String JABONGMULTIPLEURLWITHONLYONEVALID = "https://jabong.go2affise.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-";
	public static final String JABONGMULTIPLEURLWITHONLYONEVALID_BILTY = "https://bit.ly/2D4rKOf";
	
	//MYNTRA & JABONG :
	public static final String JABONGANDMYNTRA_TWO_VALID_URL = "https://myntra.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-";
	public static final String JABONGANDMYNTRA_TWO_VALID_URL_BITLY = "https://bit.ly/2GaXuSw";
	
	
}