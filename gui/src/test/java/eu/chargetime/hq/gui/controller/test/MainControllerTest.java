package eu.chargetime.hq.gui.controller.test;

import eu.chargetime.hq.core.Connection;
import eu.chargetime.hq.gui.controller.MainController;
import eu.chargetime.hq.gui.view.SetupPanel;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

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
public class MainControllerTest {

    private MainController sut;

    @Mock
    private Connection connection = mock(Connection.class);

    @Mock
    private SetupPanel setupPanel = mock(SetupPanel.class);

    public MainControllerTest() {
        sut = new MainController(connection, setupPanel);
    }

    @Test
    public void connect_listenIsCalled() throws Exception {
        // When
        //sut

        //Then
        verify(connection, times(1)).isConnected();
    }



}