package example.com;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestCityScore {
    private CityScore cityScore = new CityScore();
    private static final String pathToInputFile = "src\\main\\resources\\input.txt";
    private static final String pathToOutputFile = "src\\main\\resources\\output.txt";

    @Test
    public void test() {
        List<String> initList = cityScore.getDataFromFile(pathToInputFile);
        List<String> resultList = cityScore.findMaxScore(initList);
        cityScore.setDataToFile(resultList, pathToOutputFile);

        List<String> expectedResultList = Arrays.asList("Onion", "Nadean", "Notebook", "Kiev", "Volcano", "Olympia");
        Assert.assertEquals(expectedResultList, resultList);
    }
}