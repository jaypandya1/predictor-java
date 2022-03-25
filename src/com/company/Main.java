package com.company;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Main {



    static void getAge(String userName) throws IOException, ParseException {


        URL url = new URL("https://api.agify.io/?name=" + userName);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();


        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String age = "";
            Scanner scanner = new Scanner(url.openStream());


            while (scanner.hasNext()) {
                age += scanner.nextLine();
            }


            scanner.close();


            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(age);

            System.out.println(userName+"'s age is "+data_obj.get("age"));
        }
    }

    static void getGender(String userName) throws IOException, ParseException {
        URL url1 = new URL("https://api.genderize.io/?name=" + userName);

        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            String gender = "";
            Scanner scanner = new Scanner(url1.openStream());


            while (scanner.hasNext()) {
                gender += scanner.nextLine();
            }


            scanner.close();


            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(gender);

            System.out.println(userName+" is "+ data_obj.get("gender"));
        }
    }



    public static void main(String[] args) throws IOException, ParseException {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter your name");
        String userName = myObj.nextLine();

getAge(userName);
getGender(userName);
    }
}
