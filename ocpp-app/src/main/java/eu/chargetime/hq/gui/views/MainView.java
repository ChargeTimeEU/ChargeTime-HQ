package eu.chargetime.hq.gui.views;
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

import eu.chargetime.hq.gui.mediators.IMainMediator;
import eu.chargetime.hq.gui.mediators.IMainMediatorFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainView implements IMainView {

    private IMainMediator mediator;
    private Label labelStatus;

    public MainView(IMainMediatorFactory mediatorFactory) {
        if (mediatorFactory == null)
            throw new IllegalArgumentException("mediatorFactory cannot be null");

        mediator = mediatorFactory.createMediator(this);
    }

    @Override
    public Pane getView() {
        HBox hBox = new HBox();
        setLayout(hBox);
        addSetup(hBox);

        labelStatus = new Label("Status: Ready");
        hBox.getChildren().add(labelStatus);
        return hBox;
    }

    @Override
    public void setStatus(String status) {
        labelStatus.setText(String.format("Status: %s", status));
    }

    private void setLayout(HBox hBox) {
        hBox.setPadding(new Insets(15,12,15,12));
        hBox.setSpacing(10);
    }

    private void addSetup(HBox hBox) {

        ObservableList<String> serverTypes = FXCollections.observableArrayList("json", "SOAP");
        ComboBox serverType = new ComboBox(serverTypes);

        TextField textHostname = new TextField();

        TextField textPort = new TextField();

        Button buttonSetup = new Button("Listen");
        buttonSetup.setPrefSize(100, 20);
        buttonSetup.setOnAction(event -> mediator.doConnect(serverType.getValue().toString(), textHostname.getText(), textPort.getText()));

        hBox.getChildren().addAll(serverType, textHostname, textPort, buttonSetup);
    }
}
