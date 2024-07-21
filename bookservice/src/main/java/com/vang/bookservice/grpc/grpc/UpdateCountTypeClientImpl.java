package com.vang.bookservice.grpc.grpc;

import com.vang.bookservice.grpc.gen.UpdateCountTypeGrpc;
import com.vang.bookservice.grpc.gen.UpdateCountTypeReply;
import com.vang.bookservice.grpc.gen.UpdateCountTypeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCountTypeClientImpl {

    private final UpdateCountTypeGrpc.UpdateCountTypeBlockingStub updateCountTypeBlockingStub;

    @Autowired
    public UpdateCountTypeClientImpl(UpdateCountTypeGrpc.UpdateCountTypeBlockingStub updateCountTypeBlockingStub) {
        this.updateCountTypeBlockingStub = updateCountTypeBlockingStub;
    }

    public boolean updateCountType(String typeId, int typeUpdate) {

        UpdateCountTypeReply reply = updateCountTypeBlockingStub.updateCount(UpdateCountTypeRequest.newBuilder().setTypeId(typeId).setTypeUpdate(typeUpdate).build());
        return reply.getStatus();
    }

}