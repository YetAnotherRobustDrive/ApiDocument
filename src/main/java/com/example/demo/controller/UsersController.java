package com.example.demo.controller;

import com.example.demo.ErrorDto;
import com.example.demo.ResisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Operation(summary = "유저를 만듭니다", description = "유저를 받아서 만들 수 있습니다. 관리자만 접근할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 생성 성공",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Id중복, 비밀번호 정책 위반, 유효하지 않은 입력",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @PostMapping
    public ResponseEntity<?> read(ResisterDto resisterDto) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "유저 정보를 가져옵니다.", description = "특권 사용자와 관리자가 접근할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 가져오기 성공",
                    content = @Content(schema = @Schema(implementation = UserInfoDto.class))),
            @ApiResponse(responseCode = "404", description = "user id가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @GetMapping("/{userid}")
    public ResponseEntity<?> get(@PathVariable String userid) {
        return ResponseEntity.ok().body("OK");
    }

    private class UserInfoDto {
        private String name;
        private String id;
    }
    @Operation(summary = "유저를 삭제합니다.", description = "관리자가 접근할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 삭제 성공",
                    content = @Content(schema = @Schema(implementation = UserInfoDto.class))),
            @ApiResponse(responseCode = "404", description = "user id가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @RequestMapping(value = "/{userid}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> delete(@PathVariable String userid) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "유저 정보를 바꿉니다.", description = "특권 사용자와 관리자가 접근할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 정보 가져오기 성공",
                    content = @Content(schema = @Schema(implementation = UserInfoDto.class))),
            @ApiResponse(responseCode = "404", description = "user id가 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "Id중복, 비밀번호 정책 위반, 유효하지 않은 입력",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @PostMapping("/{userid}/update")
    public ResponseEntity<?> update(@PathVariable String userid) {
        return ResponseEntity.ok().body("OK");
    }
}
