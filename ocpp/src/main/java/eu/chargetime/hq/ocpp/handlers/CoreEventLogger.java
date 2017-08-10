package eu.chargetime.hq.ocpp.handlers;
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

import eu.chargetime.hq.ocpp.ILogService;
import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;

import java.util.UUID;

public class CoreEventLogger implements ServerCoreEventHandler {

    private final ServerCoreEventHandler handler;
    private final ILogService service;

    public CoreEventLogger(ServerCoreEventHandler handler, ILogService service) {
        this.handler = handler;
        this.service = service;
    }

    @Override
    public AuthorizeConfirmation handleAuthorizeRequest(UUID uuid, AuthorizeRequest request) {
        service.logRequest(uuid, request);
        AuthorizeConfirmation confirmation = handler.handleAuthorizeRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }

    @Override
    public BootNotificationConfirmation handleBootNotificationRequest(UUID uuid, BootNotificationRequest request) {
        service.logRequest(uuid, request);
        BootNotificationConfirmation confirmation = handler.handleBootNotificationRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(UUID uuid, DataTransferRequest request) {
        service.logRequest(uuid, request);
        DataTransferConfirmation confirmation = handler.handleDataTransferRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }

    @Override
    public HeartbeatConfirmation handleHeartbeatRequest(UUID uuid, HeartbeatRequest request) {
        service.logRequest(uuid, request);
        HeartbeatConfirmation confirmation = handler.handleHeartbeatRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }

    @Override
    public MeterValuesConfirmation handleMeterValuesRequest(UUID uuid, MeterValuesRequest request) {
        service.logRequest(uuid, request);
        MeterValuesConfirmation confirmation = handler.handleMeterValuesRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }

    @Override
    public StartTransactionConfirmation handleStartTransactionRequest(UUID uuid, StartTransactionRequest request) {
        service.logRequest(uuid, request);
        StartTransactionConfirmation confirmation = handler.handleStartTransactionRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }

    @Override
    public StatusNotificationConfirmation handleStatusNotificationRequest(UUID uuid, StatusNotificationRequest request) {
        service.logRequest(uuid, request);
        StatusNotificationConfirmation confirmation = handler.handleStatusNotificationRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }

    @Override
    public StopTransactionConfirmation handleStopTransactionRequest(UUID uuid, StopTransactionRequest request) {
        service.logRequest(uuid, request);
        StopTransactionConfirmation confirmation = handler.handleStopTransactionRequest(uuid, request);
        service.logConfirmation(uuid, confirmation);
        return confirmation;
    }
}
