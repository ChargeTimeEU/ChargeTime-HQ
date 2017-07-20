package eu.chargetime.hq.ocpp.test;
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

import eu.chargetime.hq.ocpp.OCPPServerService;
import eu.chargetime.hq.ocpp.OCPPServerFactory;
import eu.chargetime.hq.ocpp.OCPPType;
import eu.chargetime.ocpp.ServerEvents;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OCPPServerTest {

    private OCPPServerService sut;

    @Mock
    OCPPServerFactory agentFactory = mock(OCPPServerFactory.class);

    @Mock
    ServerEvents serverEvents = mock(ServerEvents.class);

    public OCPPServerTest() {
        sut = new OCPPServerService(serverEvents, agentFactory);
    }

    @Test
    public void connect_jsonType_createsJson() {
        // Given
        OCPPType type = OCPPType.json;
        String host = "";
        int port = 0;

        // When
        sut.connect(type, host, port);

        // Then
        verify(agentFactory).create(type);
    }

}