package pl.gotowala;

import org.junit.Assert;
import org.junit.Test;
import pl.gotowala.functionality.ExchangeRatesProcessing;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRatesProcessingTest {

    @Test
    public void given_EmptyArrayList_When_UseGetAverageOfRates_Then_Result_0() {
        double actual = ExchangeRatesProcessing.getAverageOfRates(new ArrayList<>());
        double expected = 0;
        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void given_ArrayList13_When_UseGetAverageOfRates_Then_Result_2() {
        List<Double> test = new ArrayList<>();
        test.add(1.);
        test.add(3.);
        double actual = ExchangeRatesProcessing.getAverageOfRates(test);
        double expected = 2;
        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void given_ArrayList_With_null_When_UseGetAverageOfRates_Then_Result_0() {
        List<Double> test = new ArrayList<>();
        test.add(1.);
        test.add(null);
        double actual = ExchangeRatesProcessing.getAverageOfRates(test);
        double expected = 0;
        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void given_EmptyArrayList_When_UseGetStandardDeviationOfRates_Then_Result_0() {
        double actual = ExchangeRatesProcessing.getStandardDeviationOfRates(new ArrayList<>());
        double expected = 0;
        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void given_ArrayList11_When_UseGetStandardDeviationOfRates_Then_Result_0() {
        List<Double> test = new ArrayList<>();
        test.add(1.);
        test.add(1.);
        double actual = ExchangeRatesProcessing.getStandardDeviationOfRates(test);
        double expected = 0;
        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void given_ArrayList_With_null_When_UseGetStandardDeviationOfRates_Then_Result_0() {
        List<Double> test = new ArrayList<>();
        test.add(1.);
        test.add(null);
        double actual = ExchangeRatesProcessing.getStandardDeviationOfRates(test);
        double expected = 0;
        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void given_Value_2_226_Places_2_When_UseRound_Then_Result_2_23() {
        double actual = ExchangeRatesProcessing.round(2.226, 2);
        double expected = 2.23;
        Assert.assertEquals(expected, actual, 0.0001);
    }


}
