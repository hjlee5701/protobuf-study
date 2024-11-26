package com.example.study.client;

import com.example.study.grpc.SimpleGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ClientConfig {

    @Value ("${grpc.server.address}")
    private String serverAddress;

    @Value("${grpc.server.port}")
    private int serverPort;

    /**
     * 생성된 채널을 기반으로 Stub 은 gRPC 서버에 대한 요청(통신)을 수행
     */
    @Bean
    public SimpleGrpc.SimpleBlockingStub gRpcChannel() {
        Channel gRpcChannel = ManagedChannelBuilder
                .forAddress(serverAddress, serverPort)
                .usePlaintext() // TLS(SSL)를 비활성화하여 HTTP/2 기반의 일반 텍스트 통신을 설정
                .build();
        return SimpleGrpc.newBlockingStub(gRpcChannel);
    }
}
