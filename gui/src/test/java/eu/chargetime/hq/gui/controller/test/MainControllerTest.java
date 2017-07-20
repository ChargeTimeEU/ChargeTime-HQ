package eu.chargetime.hq.gui.controller.test;
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

import eu.chargetime.hq.gui.IViewComposite;
import eu.chargetime.hq.gui.IViewFactory;
import eu.chargetime.hq.gui.controller.MainController;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class MainControllerTest {

    private MainController sut;

    @Mock
    private IViewComposite mainView = mock(IViewComposite.class);

    @Mock
    private IViewFactory viewFactory = mock(IViewFactory.class);

    public MainControllerTest() {
        sut = new MainController(mainView, viewFactory);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mainController_nullNull_throwsException() throws Exception {
        // When
        new MainController(null, null);

        // Then
        // Throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void mainController_viewAndNull_throwsException() throws Exception {
        // When
        new MainController(mainView, null);

        // Then
        // Throws IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void mainController_nullAndFactory_throwsException() throws Exception {
        // When
        new MainController(null, viewFactory);

        // Then
        // Throws IllegalArgumentException
    }

    @Test
    public void load_callsShow() {
        // When
        sut.start();

        // Then
        verify(mainView).compose();
    }
}