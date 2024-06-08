package com.lgcns.tct.sp.sub3.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgcns.tct.sp.sub3.StateManager;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ParallelState extends State {

    private List<State> branches;

    public ParallelState(String name, List<State> branches) {
        super(name);
        this.branches = branches;
    }

    @Override
    public String run() throws Exception {
        CountDownLatch latch = new CountDownLatch(branches.size());
        for (State branch : branches) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        String result = branch.run();
                        //System.out.println(">> ParallelState.run - result : " + result);
                        Type type = new TypeToken<Map<String, String>>() {}.getType();
                        StateManager.results.add(new Gson().fromJson(result, type));
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        latch.await();

        System.out.println(">> StateManager.results[B] : " + StateManager.results);
        Collections.sort(StateManager.results, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> map1, Map<String, String> map2) {
                return map1.get("total").compareTo(map2.get("total")); // 오름차순 (map2, map1 자리변경 시 내림차순)
            }
        });
        System.out.println(">> StateManager.results[A] : " + StateManager.results);

        return new Gson().toJson(StateManager.results);
    }

    @Override
    public String toString() {
        return "ParallelState{" +
                "branches=" + branches +
                '}';
    }
}
