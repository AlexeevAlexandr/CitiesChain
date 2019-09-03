package example.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    private List<String> collectCities(List<String> cities){
        List<String> result = new ArrayList<>();
        List<String> list = new ArrayList<>(List.copyOf(cities));

        String compareCity;
        for (String city : cities) {
            for (int j = 0; j < list.size(); j++) {
                compareCity = list.get(j);
                if (!city.equals(compareCity)) {
                    String a = String.valueOf(city.charAt(city.length() - 1));
                    String b = String.valueOf(compareCity.charAt(0));
                    if (a.equalsIgnoreCase(b)) {
                        if (!result.contains(city)) {
                            result.add(city);
                        }
                        result.add(compareCity);
                        list.remove(compareCity);
                        city = compareCity;
                        j = 0;
                    }
                }
            }



        }
        return result;
    }

    public static void main(String[] args) {
        A a = new A();
        List<String> list = a.getCities();
        System.out.println(list + " ===== initial arr");
        System.out.println(a.collectCities(list) + " ===== result");
    }
}
