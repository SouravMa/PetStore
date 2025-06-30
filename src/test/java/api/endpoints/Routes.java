package api.endpoints;

public class Routes {
	
	public static String baseUrl= "https://petstore.swagger.io/v2";
	
	// for user
	
	public static String postUrl= baseUrl+"/user";
	public static String getUrl= baseUrl+"/user/{username}";
	public static String updateUrl= baseUrl+"/user/{username}";
	public static String deleteUrl= baseUrl+"/user/{username}";
	
	// for store
	
	public static String postUrlStore= baseUrl+"/store/order";
	public static String getUrlStore= baseUrl+"/store/order/{orderId}";
	public static String deleteUrlStore= baseUrl+"/store/order/{orderId}";
	
}
