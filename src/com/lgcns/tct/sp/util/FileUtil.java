package com.lgcns.tct.sp.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class FileUtil {

    /**
     * rootPath 폴더의 파일 목록 반환
     *
     * @param rootPath rootPath
     * @return 파일 목록
     */
    public static List<String> fileDirList(String rootPath) throws Exception {
        List<String> list = new ArrayList<>();

        File directory = new File(rootPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    //System.out.println("[" + file.getName() + "]");
                } else {
                    //System.out.println(file.getName());
                    list.add(rootPath + "/" + file.getName());
                }
            }
        }

        return list;
    }

    /**
     * txt 파일을 읽어 Map List를 반환
     *
     * @param path  파일 경로(예: "SAMPLE.TXT" 입력 시 프로젝트 루트 경로에서 찾음)
     * @param regex 한 줄의 아이템 별 구분자(예: "#")
     * @param keys  Map의 Key 리스트(예: new String[]{"Name", "Type", "Url"})
     * @return Map List
     * @throws Exception throws any Exceptions...
     */
    public static List<Map<String, Object>> txt2mapList(String path, String regex, String[] keys) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();

        for (String line : Files.readAllLines(Paths.get(path))) {
            Map<String, Object> map = new LinkedHashMap<>();

            String[] elements = line.split(regex);
            for (int i = 0; i < elements.length; i++) {
                map.put(keys[i], elements[i]);
            }
            list.add(map);
        }

        return list;
    }

    /**
     * json 파일을 읽어 Map List를 반환
     *
     * @param path 파일 경로(예: "SAMPLE.JSON" 입력 시 프로젝트 루트 경로에서 찾음)
     * @return Map List
     * @throws Exception throws any Exceptions...
     */
    public static List<Map<String, Object>> json2mapList(String path) throws Exception {
        Type type = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        return Collections.synchronizedList(new Gson().fromJson(new String(Files.readAllBytes(Paths.get(path))), type));
    }

    /**
     * file 쓰기
     *
     * @param path 파일 경로
     * @param data 데이터
     * @throws Exception throws any Exceptions...
     */
    public static void writeTxtFile(String path, String data) throws Exception {
        if (!(new File(path).exists())) {
            Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.CREATE);
        } else {
            Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static String map2json(Map<String, Object> map) throws Exception {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }

}
