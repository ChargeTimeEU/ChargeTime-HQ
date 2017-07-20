package eu.chargetime.hq.gui.mediators;
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

import eu.chargetime.hq.gui.views.IMainView;
import eu.chargetime.hq.ocpp.OCPPType;
import eu.chargetime.hq.ocpp.commands.ServerCommandFactory;
import eu.chargetime.hq.ocpp.commands.ServerConnect;

public class MainMediator implements IMainMediator {

    private final IMainView view;
    private final ServerCommandFactory commandFactory;

    public MainMediator(IMainView view, ServerCommandFactory commandFactory) {
        if (view == null)
            throw new IllegalArgumentException("view cannot be null");

        if (commandFactory == null)
            throw new IllegalArgumentException("commandFactory cannot be null");

        this.view = view;
        this.commandFactory = commandFactory;
    }

    @Override
    public void doConnect(String serverType, String hostname, String port) {
        OCPPType type = Enum.valueOf(OCPPType.class, serverType);
        int portNumber = Integer.parseInt(port);

        ServerConnect command = commandFactory.createServerConnect(type, hostname, portNumber);
        command.setOnRunning(event -> view.setStatus("Connecting..."));
        command.setOnSucceeded(event -> view.setStatus("Connected"));
        command.setOnFailed(event -> view.setStatus("Failed!"));
        command.start();
    }
}
