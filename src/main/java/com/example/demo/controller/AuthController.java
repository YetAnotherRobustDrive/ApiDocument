package com.example.demo.controller;

import com.example.demo.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Operation(summary = "로그인", description = "로그인을 하고 jwt token을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "존재하지 않는 ID이거나 비밀번호가 틀림",
                content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                content = @Content(schema = @Schema(implementation = JwtToken.class)))
    })
    @PostMapping("/longin")
    public ResponseEntity<?> login(LoginDto loginDto) {
        return ResponseEntity.ok().body("OK");
    }

    @Getter
    public static class LoginDto{
        public String id;
        public String password;
    }

    @Getter
    public static class JwtToken {
        private String grantType;
        private String accessToken;
        private long accessTokenExpiresIn;
        private String refreshToken;
    }

    @Operation(summary = "등록", description = "새로운 유저를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "Id중복, 비밀번호 정책 위반, 유효하지 않은 입력",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(ResisterDto resisterDto) {
        return ResponseEntity.ok().body(null);
    }

    @Getter
    private static class ResisterDto {
        private String name;
        private String id;
        private String password;
    }

    @Operation(summary = "회원 탈퇴", description = "가입되어있는 회원을 탈퇴합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content),
            @ApiResponse(responseCode = "401", description = "로그인 안되어 있음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping("/deregister")
    public ResponseEntity<?> deregister() {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "로그아웃", description = "로그인 된 회원을 로그아웃 합니다. 같은 jwt token으로 접근할 수 없게 됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공", content = @Content)
    })
    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().body("OK");
    }
}
