package com.example.study.server;


import com.example.study.grpc.HelloReply;
import com.example.study.grpc.HelloRequest;
import com.example.study.grpc.SimpleGrpc;
import org.springframework.stereotype.Service;

@Service
public class ServerService extends SimpleGrpc.SimpleImplBase {
    /**
     * Client 에서 보낸 요청에 대한 응답을 반환 (test -> Hello test)
        - StreamObserver : 서버-클라이언트 간 데이터 스트리밍을 처리하는 인터페이스
        - onNext : 스트림에 데이터 추가 후 전송 (호출 순서 보장, 여러번의 추가-전송이 독립적으로 이뤄짐)
        - onCompleted : 스트림 종료
     * @param request
     * @param responseObserver
     */
    @Override
    public void sayHello(HelloRequest request, io.grpc.stub.StreamObserver<HelloReply> responseObserver) {
        String clientName = request.getName();

        for(int cnt: new int[]{1, 2, 3}){
            HelloReply reply = HelloReply.newBuilder().setMessage( cnt +" : Hello "+ clientName).build();
            responseObserver.onNext(reply);

            System.out.println(reply.getMessage());
        }
        responseObserver.onCompleted();

    }

}
