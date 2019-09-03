package example.com;

import java.io.IOException;
import java.nio.file.Files;
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
        try {
            Files.write(Paths.get("src\\main\\resources\\output.txt"), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> collectCities(List<String> cities){
        List<String> temp = new ArrayList<>();
        List<String> result = new ArrayList<>();
        List<String> list = new ArrayList<>(List.copyOf(cities));

        String compareCity;
        String city;
        int lengthOfCities = 0;
        for (String c : cities) {
            city = c;
            for (int j = 0; j < list.size(); j++) {
                compareCity = list.get(j);
                if (!city.equals(compareCity)) {
                    String a = String.valueOf(city.charAt(city.length() - 1));
                    String b = String.valueOf(compareCity.charAt(0));
                    if (a.equalsIgnoreCase(b)) {
                        if (!temp.contains(city)) {
                            temp.add(city);
                        }
                        temp.add(compareCity);
                        list.remove(city);
                        list.remove(compareCity);
                        city = compareCity;
                        j = 0;
                    }
                }
            }


            int count = temp.stream().map(String::valueOf).collect(Collectors.joining()).length();
            if (count > lengthOfCities){
                lengthOfCities = count;
                result = new ArrayList<>(List.copyOf(temp));
            }
            temp.clear();
            list = new ArrayList<>(List.copyOf(cities));

        }
        System.out.println("Cities list with max score = " + result);
        System.out.println("SCORE = " + lengthOfCities);
        return result;
    }

    public static void main(String[] args) {
        A a = new A();
        List<String> initList = a.getCities();
        List<String> resultList = a.collectCities(initList);
        a.setCities(resultList);
    }
}
