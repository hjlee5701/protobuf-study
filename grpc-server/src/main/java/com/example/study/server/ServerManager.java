package com.example.study.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ServerManager {

    @Value("${grpc.server.port}")
    private int serverPort;

    private Server gRpcServer;

    private final ServerService serverService;

    public void gRpcServerStart() throws IOException {
        gRpcServer = ServerBuilder
                .forPort(serverPort)
                .addService(serverService)
                .build().start();
        System.out.println("Start gRpcServer!");
        // 비동기로 gRPC 서버를 실행
        new Thread(() -> {
            try {
                // 대기 상태 유지
                gRpcServer.awaitTermination();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void gRpcServerStop() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Try gRpcServer shutdown...");
                gRpcServer.shutdown();
                if(!gRpcServer.awaitTermination(30, TimeUnit.SECONDS)) {
                    gRpcServer.shutdownNow();
                }
                System.out.println("Complete gRpcServer shutdown.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                })
        );
    }

}
