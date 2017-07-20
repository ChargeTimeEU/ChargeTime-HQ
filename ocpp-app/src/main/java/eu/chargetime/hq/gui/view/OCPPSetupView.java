package eu.chargetime.hq.gui.view;
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

import eu.chargetime.hq.gui.IViewComponent;
import eu.chargetime.hq.ocpp.OCPPType;

import javax.swing.*;
import java.awt.*;

public class OCPPSetupView implements IViewComponent {
    private final Container frame;


    public OCPPSetupView(Container frame) {
        if (frame == null)
            throw new IllegalArgumentException();

        this.frame = frame;
    }

    @Override
    public void compose() {
        JPanel panel = new JPanel();

        addHeadline(panel);
        addSetupFields(panel);
        addSelectButton(panel);

        frame.add(panel);
    }

    private void addSetupFields(JPanel panel) {
        JComboBox serverType = new JComboBox(OCPPType.values());
        panel.add(serverType);

        JTextField hostAddress = new JTextField(20);
        hostAddress.setToolTipText("Host address");
        panel.add(hostAddress);

        JTextField port = new JTextField(20);
        port.setToolTipText("Port number");
        panel.add(port);
    }

    private void addSelectButton(JPanel panel) {
        JButton selectButton = new JButton("Select");
        panel.add(selectButton);
    }

    private void addHeadline(JPanel panel) {
        JLabel label = new JLabel("OCPP version 1.6");
        panel.add(label);
    }
}
