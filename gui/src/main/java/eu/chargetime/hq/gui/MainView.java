package eu.chargetime.hq.gui;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainView implements IViewComposite {

    private final Container frame;
    private List<IViewComponent> components;

    public MainView(Container frame) {
        if (frame == null)
            throw new IllegalArgumentException();

        this.frame = frame;
        components = new ArrayList<>();
    }

    @Override
    public void compose() {
        for (IViewComponent component: components) {
            component.compose();
        }
        frame.setVisible(true);
    }

    @Override
    public void add(IViewComponent component) {
        components.add(component);
    }

    @Override
    public void remove(IViewComponent component) {
        components.remove(component);
    }
}
