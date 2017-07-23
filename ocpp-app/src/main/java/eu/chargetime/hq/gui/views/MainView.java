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
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainView implements IMainView {

    private IMainMediator mediator;
    private Label labelStatus;
    private TextArea textLog;

    public MainView(IMainMediatorFactory mediatorFactory) {
        if (mediatorFactory == null)
            throw new IllegalArgumentException("mediatorFactory cannot be null");

        mediator = mediatorFactory.createMediator(this);
    }

    @Override
    public Pane getView() {
        VBox rel = new VBox();

        HBox firstRow = new HBox();
        setLayout(firstRow);
        addSetup(firstRow);

        HBox secondRow = new HBox();
        addLog(secondRow);

        labelStatus = new Label("Status: Ready");
        firstRow.getChildren().add(labelStatus);

        rel.getChildren().addAll(firstRow, secondRow);
        return rel;
    }

    private void addLog(HBox hBox) {
        textLog = new TextArea();
        hBox.getChildren().add(textLog);
    }

    @Override
    public void setStatus(String status) {
        labelStatus.setText(String.format("Status: %s", status));
    }

    @Override
    public void appendLog(String logEntry) {
        String text = textLog.getText();
        textLog.setText(logEntry + text);
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
