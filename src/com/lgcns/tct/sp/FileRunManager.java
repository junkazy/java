package com.lgcns.tct.sp;

import com.lgcns.tct.sp.util.FileUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileRunManager {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        // INPUT 폴더의 파일 목록 반환
        List<String> fileList = FileUtil.fileDirList("INPUT");
        System.out.println(fileList);

        // txt 파일을 읽어 Map List를 반환
        List<Map<String, Object>> txtMapList = FileUtil.txt2mapList("INPUT/SAMPLE.TXT", "#", new String[]{"name", "type", "url"});
        System.out.println(txtMapList);

        // json 파일을 읽어 Map List를 반환
        List<Map<String, Object>> jsonMapList = FileUtil.json2mapList("INPUT/SAMPLE.JSON");
        System.out.println(jsonMapList);

        // file 쓰기
        FileUtil.writeTxtFile("OUTPUT/SAMPLE.TXT", "aaaa\n");
        FileUtil.writeTxtFile("OUTPUT/SAMPLE.TXT", "bbbb\n");
        FileUtil.writeTxtFile("OUTPUT/SAMPLE.TXT", "cccc\n");

//        while (true) {
//            String request = scanner.nextLine();
//            System.out.println(">> INPUT : " + request);
//
//            for (Map<String, Object> map : jsonMapList) {
//                Object obj = map.get(request);
//                System.out.println(">> Map key(" + request + ") : value(" + obj + ")");
//            }
//        }
    }

}
