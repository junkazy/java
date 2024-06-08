package com.lgcns.tct.sp.sub3;

import com.lgcns.tct.sp.sub3.microservice.AddServer;
import com.lgcns.tct.sp.sub3.microservice.CreateServer;
import com.lgcns.tct.sp.sub3.server.EnginServer;

public class RunManager {


    public static void main(String[] args) throws Exception {

        ValiableManager.load();

        StateManager.load();

        EnginServer enginServer = new EnginServer();
        enginServer.start();

        CreateServer createServer = new CreateServer();
        createServer.start();

        AddServer addServer = new AddServer();
        addServer.start();
    }

}
