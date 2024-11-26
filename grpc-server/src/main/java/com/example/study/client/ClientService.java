package com.example.study.client;

import com.example.study.grpc.HelloReply;
import com.example.study.grpc.HelloRequest;
import com.example.study.grpc.SimpleGrpc;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    /**
     stub : 클라이언트가 서버의 메소드를 호출하기 위한 프록시 객체
        - gRPC 서비스 정의에 따라 자동 생성
        - stub 을 사용해 원격 프로시저 호출 (RPC) 를 한다.
        - 서버와 클라이언트 사이의 중간 계층
        - 서버와의 통신 처리 및 역/직렬화 자동 수행
        - 종류
            : BlockingStub, FutureStub, AsyncStub
     */
    private final SimpleGrpc.SimpleBlockingStub simpleStub;

    public String getMessage(String name) {
        try{
            // stub 을 사용해 응답값 반환 받음
            HelloReply response = this.simpleStub.sayHello(HelloRequest.newBuilder().setName(name).build());

            // 테스트 결과 반환
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}
