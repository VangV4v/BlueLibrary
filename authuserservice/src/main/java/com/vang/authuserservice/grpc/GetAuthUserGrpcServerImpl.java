package com.vang.authuserservice.grpc;

import com.vang.authuserservice.common.ServiceCommon;
import com.vang.authuserservice.grpc.gen.GetAuthUserGrpc;
import com.vang.authuserservice.grpc.gen.GetAuthUserReply;
import com.vang.authuserservice.grpc.gen.GetAuthUserRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@GrpcService
public class GetAuthUserGrpcServerImpl extends GetAuthUserGrpc.GetAuthUserImplBase {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public GetAuthUserGrpcServerImpl(RedisTemplate<String, String> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }

    @Override
    public void getAutInfo(GetAuthUserRequest request, StreamObserver<GetAuthUserReply> responseObserver) {

        String username = redisTemplate.opsForValue().get(ServiceCommon.REDIS_KEY_USERNAME);
        String expirationDate = redisTemplate.opsForValue().get(ServiceCommon.REDIS_KEY_USERNAME_EXPIRATION);
        Date dateExpiration = StringUtils.isEmpty(expirationDate) ? new Date(System.currentTimeMillis()) : new Date(Long.parseLong(expirationDate));
        GetAuthUserReply reply;
        if(StringUtils.isEmpty(username) || new Date().after(dateExpiration)) {

            reply = GetAuthUserReply.newBuilder().setStatus(false).build();
        } else {

            reply = GetAuthUserReply.newBuilder().setStatus(true).setUsername(username).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}