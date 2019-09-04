package example.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class A {

    private List<String> getCities(){
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get("src\\main\\resources\\input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void setCities(List<String> list){
        Path path = Paths.get("src\\main\\resources\\output.txt");
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
        int lengthOfCities = 0;
        for (String city : cities) {
            for (int j = 0; j < copyCitiesList.size(); j++) {
                compareCity = copyCitiesList.get(j);
                if (!city.equals(compareCity)) {
                    String a = String.valueOf(city.charAt(city.length() - 1));
                    String b = String.valueOf(compareCity.charAt(0));
                    if (a.equalsIgnoreCase(b)) {
                        if (!tempList.contains(city)) {
                            tempList.add(city);
                        }
                        tempList.add(compareCity);
                        copyCitiesList.remove(city);
                        copyCitiesList.remove(compareCity);
                        city = compareCity;
                        j = 0;
                    }
                }
            }


            int count = tempList.stream().map(String::valueOf).collect(Collectors.joining()).length();
            if (count > lengthOfCities){
                lengthOfCities = count;
                resultList = new ArrayList<>(List.copyOf(tempList));
            }
            tempList.clear();
            copyCitiesList = new ArrayList<>(List.copyOf(cities));

        }
        System.out.println("Cities copyCitiesList with max score = " + resultList);
        System.out.println("SCORE = " + lengthOfCities);
        return resultList;
    }

    public static void main(String[] args) {
        A a = new A();
        List<String> initList = a.getCities();
        List<String> resultList = a.collectCities(initList);
        a.setCities(resultList);
    }
}
