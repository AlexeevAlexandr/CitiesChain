package example.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solver {

    static List<String> solve(List<String> availableCities) {

        List<String> resultList = new ArrayList<>();
        String compareCity = "";
        String compareCityFirstLetter = "";
        String city = Collections.max(availableCities, Comparator.comparing(String::length));

        for (int j = 0; j < availableCities.size(); ) {
            String cityLastLetter = city.substring(city.length() - 1);

            List<String> allCitiesWhichStartsFromCityLastLetter = availableCities.stream().filter(e -> e.toLowerCase().startsWith(cityLastLetter)).collect(Collectors.toList());
            if (!allCitiesWhichStartsFromCityLastLetter.isEmpty()) {
                compareCity = Collections.max(allCitiesWhichStartsFromCityLastLetter, Comparator.comparing(String::length));
                compareCityFirstLetter = compareCity.substring(0, 1);
            }

            if (!city.equals(compareCity) && cityLastLetter.equalsIgnoreCase(compareCityFirstLetter)) {
                if (!resultList.contains(city)) {
                    resultList.add(city);
                }
                resultList.add(compareCity);
                availableCities.remove(city);
                availableCities.remove(compareCity);
                city = compareCity;
                j = 0;
            } else {
                j ++;
            }
        }

        return resultList;
    }
}
