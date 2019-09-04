package example.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class CityScore {

    List<String> getDataFromFile(String pathString){
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get(pathString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    void setDataToFile(List<String> list, String pathString){
        Path path = Paths.get(pathString);
        try {
            if (Files.notExists(path)){
                Files.createFile(path);
            }
            Files.write(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> collectCities(List<String> cities){
        List<String> tempList = new ArrayList<>();
        List<String> resultList = new ArrayList<>();
        List<String> copyCitiesList = new ArrayList<>(List.copyOf(cities));

        String compareCity;
        String cityLastChar;
        String compareCityFirstChar;
        int score = 0;
        int lengthOfCities;
        for (String city : cities) {
            for (int j = 0; j < copyCitiesList.size(); ) {
                compareCity = copyCitiesList.get(j);
                cityLastChar = city.substring(city.length() - 1);
                compareCityFirstChar = compareCity.substring(0, 1);
                if (!city.equals(compareCity) && cityLastChar.equalsIgnoreCase(compareCityFirstChar)) {
                    if (!tempList.contains(city)) {
                        tempList.add(city);
                    }
                    tempList.add(compareCity);
                    copyCitiesList.remove(city);
                    copyCitiesList.remove(compareCity);
                    city = compareCity;
                    j = 0;
                } else {
                    j ++;
                }
            }
            lengthOfCities = tempList.stream().map(String::valueOf).collect(Collectors.joining()).length();
            if (lengthOfCities > score){
                score = lengthOfCities;
                resultList = new ArrayList<>(List.copyOf(tempList));
            }
            tempList.clear();
            copyCitiesList = new ArrayList<>(List.copyOf(cities));
        }
        return resultList;
    }

    List<String> findMaxScore(List<String> list){
        List<String> list1 = collectCities(list);
        Collections.reverse(list);
        List<String> list2 = collectCities(list);

        int lengthList1 = list1.stream().map(String::valueOf).collect(Collectors.joining()).length();
        int lengthList2 = list2.stream().map(String::valueOf).collect(Collectors.joining()).length();
        int score = Math.max(lengthList1, lengthList2);

        System.out.println("City list with max score = " + ((lengthList1 > lengthList2) ? list1 : list2));
        System.out.println("SCORE = " + score);
        return (lengthList1 > lengthList2) ? list1 : list2;
    }
}