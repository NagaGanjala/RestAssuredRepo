package allureexamples;

import app.MySimpleCalculator;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class CalculatorSteps {
	
	
	@Step("Adding 2 numbers: {0} & {1}")
	public double add2Numbers(double n1, double n2) {
		
		MySimpleCalculator calculate = new MySimpleCalculator();
		return calculate.add2Num(n1, n2);
	}
	
	@Attachment(value="{0}" , type = "text/plain")
	public String writeText( String message) {
		return message;
	}
	
	@Attachment(value="Image" , type = "image/png")
	public byte[] attachImage( byte[] image) {
		return image;
	}

}
