package com.lgcns.tct.sp.sub3.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgcns.tct.sp.sub3.StateManager;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelState extends State {

    private List<State> branches;

    public ParallelState(String name, List<State> branches) {
        super(name);
        this.branches = branches;
    }

    @Override
    public String run() throws Exception {
        List<Map<String, String>> results = new ArrayList<>();

        // 1. [Fixed Thread ExecutorService 구현체 생성]
        ExecutorService executorService = Executors.newFixedThreadPool(branches.size());

        // 2. [Callable 작업 제출]
        List<Future<String>> futures = new ArrayList<>();
        for (State branch : branches) {
//            Future<String> future = executorService.submit(() -> {
//                String result = branch.run();
//                return result;
//            });
//            futures.add(future);
            futures.add(executorService.submit(() -> branch.run()));
        }

        // 3. [graceful shutdown]
        executorService.shutdown();

        // 4. [병렬처리 결과 저장]
        for (Future<String> future : futures) {
            String result = future.get();
            System.out.println(">> ParallelState.run - result : " + result);
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            results.add(new Gson().fromJson(result, type));
        }
        System.out.println(">> ParallelState.run - results[B] : " + results);

        // 5. [커스텀 정렬]
        Collections.sort(results, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> map1, Map<String, String> map2) {
                return map1.get("total").compareTo(map2.get("total")); // 오름차순 (map2, map1 자리변경 시 내림차순)
            }
        });
        System.out.println(">> ParallelState.run - results[A] : " + results);

        return new Gson().toJson(results);
    }

    @Override
    public String toString() {
        return "ParallelState{" +
                "branches=" + branches +
                '}';
    }
}
