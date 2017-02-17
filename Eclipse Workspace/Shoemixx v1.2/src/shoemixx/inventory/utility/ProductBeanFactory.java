package shoemixx.inventory.utility;

import shoemixx.inventory.model.ProductBean;


public class ProductBeanFactory {
	public static ProductBean getInstance(String productCode, String productColor, int productQuantity, 
												int productSize, double productPrice){
		ProductBean shoes = new ProductBean();
		shoes.setSize(productSize);
		shoes.setQuantity(productQuantity);
		shoes.setProductCode(productCode);
		shoes.setProductColor(productColor);
		shoes.setProductPrice(productPrice);
		
		return shoes;
	}
	
}
