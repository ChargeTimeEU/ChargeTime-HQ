package eu.chargetime.hq;
/*
    ChargeTime.eu - ChargeTime HQ

    MIT License

    Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */

import eu.chargetime.hq.gui.mediators.MainLogMediator;
import eu.chargetime.hq.gui.mediators.MainMediatorFactory;
import eu.chargetime.hq.gui.views.IMainView;
import eu.chargetime.hq.gui.views.MainView;
import eu.chargetime.hq.ocpp.OCPPServerFactory;
import eu.chargetime.hq.ocpp.OCPPServerService;
import eu.chargetime.hq.ocpp.ObserverLog;
import eu.chargetime.hq.ocpp.commands.ServerCommandFactory;
import eu.chargetime.hq.ocpp.profile.CoreEventHandler;
import eu.chargetime.hq.ocpp.profile.CoreEventLogger;
import eu.chargetime.hq.ocpp.profile.ServerEventLogger;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OCPPLauncher extends Application {
    private IMainView mainView;
    private ObserverLog log;

    public static void main(String args[]) {
        new OCPPLauncher().launch();
    }

    public OCPPLauncher() {
        // Composite root

        log = new ObserverLog();
        CoreEventHandler coreEventHandler = new CoreEventHandler();
        CoreEventLogger coreEventLogger = new CoreEventLogger(coreEventHandler, log);
        ServerCoreProfile serverCoreProfile = new ServerCoreProfile(coreEventLogger);
        OCPPServerFactory ocppServerFactory = new OCPPServerFactory(serverCoreProfile);
        ServerEventLogger serverEventLogger = new ServerEventLogger(log);
        OCPPServerService ocppServerService = new OCPPServerService(serverEventLogger, ocppServerFactory);
        ServerCommandFactory serverCommandFactory = new ServerCommandFactory(ocppServerService);
        MainMediatorFactory mainMediatorFactory = new MainMediatorFactory(serverCommandFactory);
        mainView = new MainView(mainMediatorFactory);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Run program

        log.subscribe(new MainLogMediator(mainView));
        Scene scene = new Scene(mainView.getView(), 900, 700);
        stage.setTitle("ChargeTime OCPP v1.6 Server");
        stage.setScene(scene);
        stage.show();
    }

}
