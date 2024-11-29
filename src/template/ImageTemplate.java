package template;

public class ImageTemplate {

	public static String logo = getImage("/images/logo/primary-logo.png");
	
	public static String orderShoes = getImage("/images/menu/order-shoes.png");
	public static String updateShoes = getImage("/images/menu/update-shoes.png");
	public static String deleteShoes = getImage("/images/menu/delete-shoes.png");
	public static String checkoutShoes = getImage("/images/menu/checkout-shoes.png");
	public static String historyShoes = getImage("/images/menu/history-shoes.png");
	
	public static String trustworthyStore = getImage("/images/home/trustworthy.png");
	public static String exoticStyles = getImage("/images/home/exotic.png");
	public static String easyPayment = getImage("/images/home/easy-payment.png");
	
	public static String nullPicture = getImage("/images/shoes/shoes-null.jpg");
	
	private static String getImage(String file) {
		return ImageTemplate.class.getResource(file).toExternalForm();
	}

}
