package com.example.nikhil.expandablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String,List<String>> getData(){
        HashMap<String , List<String>> expandableListDetail = new HashMap<>();

        List<String> cricket = new ArrayList<>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");

        List<String> football = new ArrayList<>();
        football.add("Spain");
        football.add("Real Madrid");
        football.add("England");

        List<String> basketball = new ArrayList<>();
        basketball.add("SPain");
        basketball.add("France");
        basketball.add("Russia");

        expandableListDetail.put("CricketTeams",cricket);
        expandableListDetail.put("Football Teams",football);
        expandableListDetail.put("Basketball Teams",basketball);
        return expandableListDetail;

    }
}
