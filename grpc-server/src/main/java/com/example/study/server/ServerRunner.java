package com.example.study.server;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner
    : Spring Context 초기화 이후 실행 (모든 Spring Bean이 생성되고 의존성이 주입된 후 실행)
    : 애플리케이션 시작 후 단 1번 실행
 */
@Component
@RequiredArgsConstructor
public class ServerRunner implements CommandLineRunner {

    private final ServerManager serverManager;

    @Override
    public void run(String... args) throws Exception {
        serverManager.gRpcServerStart();
        serverManager.gRpcServerStop();
    }
}
