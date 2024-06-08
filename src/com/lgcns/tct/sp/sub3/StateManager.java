package com.lgcns.tct.sp.sub3;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lgcns.tct.sp.sub3.dto.ActionState;
import com.lgcns.tct.sp.sub3.dto.State;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateManager {

    private static Map<String, State> states = new ConcurrentHashMap<>();

    class StatesDto {
        public Map<String, StateDto> state;

        class StateDto {
            public String type;
            public String url;
            public List<String> parameters;
        }
    }

    public synchronized static State get(String name) {
        return states.get(name);
    }

    public synchronized static void load() throws JsonSyntaxException, IOException {
        StatesDto statesDto = new Gson().fromJson(new String(Files.readAllBytes(Paths.get("INPUT/STATE.JSON"))), StatesDto.class);

        for (Map.Entry<String, StatesDto.StateDto> entry : statesDto.state.entrySet()) {
            states.put(entry.getKey(), makeState(entry.getKey(), entry.getValue()));
        }

        System.out.println(">> States : " + states);
    }

    private static State makeState(String stateName, StatesDto.StateDto stateDto) {
        if ("action".equals(stateDto.type)) {
            return new ActionState(stateName, stateDto.url, stateDto.parameters);
        }
        return null;
    }
}
