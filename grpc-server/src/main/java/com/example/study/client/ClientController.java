package com.example.study.client;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // 테스트용 API
    @GetMapping("/test")
    public ResponseEntity<?> testGrpc(@RequestParam String name) {
        return ResponseEntity.ok(this.clientService.getMessage(name));
    }
}
