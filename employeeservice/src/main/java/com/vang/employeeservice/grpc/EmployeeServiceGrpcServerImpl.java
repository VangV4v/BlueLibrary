package com.vang.employeeservice.grpc;


import com.vang.employeeservice.data.EmployeeRepository;
import com.vang.employeeservice.grpc.gen.AuthenticateEmployeeGrpc;
import com.vang.employeeservice.grpc.gen.AuthenticateEmployeeReply;
import com.vang.employeeservice.grpc.gen.AuthenticateEmployeeRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class EmployeeServiceGrpcServerImpl extends AuthenticateEmployeeGrpc.AuthenticateEmployeeImplBase {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceGrpcServerImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void authenticate(AuthenticateEmployeeRequest request, StreamObserver<AuthenticateEmployeeReply> responseObserver) {

        String password = employeeRepository.getPasswordByUsername(request.getUsername());
        AuthenticateEmployeeReply reply;
        if(StringUtils.isEmpty(password)) {

            reply = AuthenticateEmployeeReply.newBuilder().setStatus(false).build();
        } else {

            reply = AuthenticateEmployeeReply.newBuilder().setStatus(true).setPassword(password).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}