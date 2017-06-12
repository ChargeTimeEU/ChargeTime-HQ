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

import eu.chargetime.hq.gui.controller.MainController;
import eu.chargetime.hq.gui.view.MainView;
import eu.chargetime.hq.gui.view.OCPPSetupPanel;
import eu.chargetime.hq.ocpp.OCPPServer;
import eu.chargetime.hq.ocpp.OCPPServerFactory;
import eu.chargetime.hq.ocpp.profile.CoreEventHandler;
import eu.chargetime.ocpp.feature.profile.ServerCoreProfile;

public class OCPPLauncher {
    private MainView mainWindow;

    public static void main(String args[]) {
        new OCPPLauncher().launch();
    }

    public OCPPLauncher() {
        // Composite root
        ServerCoreProfile coreProfile = new ServerCoreProfile(new CoreEventHandler());
        OCPPServerFactory serverFactory = new OCPPServerFactory(coreProfile);
        OCPPServer server = new OCPPServer(serverFactory);
        OCPPSetupPanel setupPanel = new OCPPSetupPanel();
        MainController mainController = new MainController(server, setupPanel);
        mainWindow = new MainView(mainController);
    }

    public void launch() {
        // Run program
        mainWindow.show();
    }
}
