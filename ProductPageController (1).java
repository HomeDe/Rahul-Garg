package src.com.test;

import java.util.ArrayList;
import java.util.Collection;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;

public class ProductPageController {
	
	//call this method from productDetail() in the ProductPageController and set the value in the model
	//Method to get customer review count between range1 and range2
	public int getCustomerReview(final Integer range1, final Integer range2) throws Exception{
		//Put a property with placeholder "text.cursewordslist" mention in local.properties file
		final String cusrsewords=configurationService.getConfiguration().getString("text.cursewordslist");
		ProductModel product=productService.getProductForCode("abc");
		Collection<CustomerReviewModel> reviews=product.getProductReviews();
		final Collection<CustomerReviewModel> finalReview=new ArrayList<>();
		for(CustomerReviewModel review:reviews ){
			if(review.getComment().contains(cusrsewords)){
				throw new Exception("Please use appropriate text for the comment");
			}
			if(Double.valueOf(review.getRating())<0){
				throw new Exception("Please Provide a good rate");
			}
           if(Double.valueOf(review.getRating())>=range1 && Double.valueOf(review.getRating())<= range2){
        	   finalReview.add(review);
			}
		}
		
		return finalReview.size();
	}
}
