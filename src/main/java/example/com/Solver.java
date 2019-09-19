package example.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solver {

    static List<String> solve(List<String> availableCities) {
        availableCities.sort(Comparator.comparing(String::length));
        Collections.reverse(availableCities);

        List<String> resultList = new ArrayList<>();
            List<String> tempList = new ArrayList<>();
            boolean check = true;
            String city = Collections.max(availableCities, Comparator.comparing(String::length));
        while (check) {
            int count = 0;
            String compareCity;
            String compareCityFirstLetter;

            for (int j = 0; j < availableCities.size(); ) {
                String cityLastLetter = city.substring(city.length() - 1);
                compareCity = availableCities.get(j);
                compareCityFirstLetter = compareCity.substring(0, 1);

                if (!city.equals(compareCity) && cityLastLetter.equalsIgnoreCase(compareCityFirstLetter)) {
                    if (!tempList.contains(city)) {
                        tempList.add(city);
                    }
                    tempList.add(compareCity);
                    availableCities.remove(city);
                    availableCities.remove(compareCity);
                    city = compareCity;
                    j = 0;
                    count++;
                } else {
                    j++;
                }
            }

            if (resultList.size() < tempList.size()){
                resultList = new ArrayList<>(List.copyOf(tempList));
            }

            if (count == 0){
                int index = tempList.size();
                tempList.remove(index - 1);
                city = tempList.get(index - 2);
            }

            if (tempList.size() < 2){
                check = false;
            }
        }
        return resultList;
    }
}
