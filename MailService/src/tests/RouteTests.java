package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import service.Route;


/**
 * Testing the Route class.
 * @author shahisha
 *
 */
public class RouteTests {

	Route r = new Route("Auckland", "Hamilton", "MainFreight", 200, 200, 3, 2, 10, 4, 24, "Domestic Land");

	@Test
	public void getCostOnWeightTest() {
		double weightValue = 5;
		assertEquals("The cost on weight is:", 15, r.getCostOnWeight(weightValue), 0);
	}

	@Test
	public void getCostOnVolumesTest() {
		double volValue = 5;
		//		volume cost is 2
		assertEquals("The cost on weight is:", 10, r.getCostOnVolume(volValue), 0);
	}

	@Test
	public void calculateDeliveryTimeTests() {
		//		frequency is 4 and estimatedDelivery time is 24
		System.out.println(r.calculateDeliveryTime());
		assertEquals("The delivery time is:", 26, r.calculateDeliveryTime(), 0);

	}

}
