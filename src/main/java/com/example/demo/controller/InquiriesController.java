package com.example.demo.controller;

import com.example.demo.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inquiries")
public class InquiriesController {
    @Operation(summary = "1대1보내기", description = "1대1 문의를 보냅니다. 연락처 정보는 필수가 아닙니다. 유저가 아니어도 사용할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "알림들을 리턴함", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> send(QueryDto queryDto) {
        return ResponseEntity.ok().body("OK");
    }

    @Getter
    private static class QueryDto {
        private String contact;
        private String content;
    }

    @Operation(summary = "특정 문의 읽기", description = "특정 문의 정보를 리턴합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 문의를 리턴함",
                content = @Content(schema = @Schema(implementation = QueryDto.class))),
            @ApiResponse(responseCode = "404", description = "특정 문의가 없습니다.", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @GetMapping("/{queryId}")
    public ResponseEntity<?> read(@PathVariable String queryId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "모든 문의 읽기", description = "로그인한 문의를 모두 리턴합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 문의 검색 성공",
                    content = @Content(array = @ArraySchema(
                            schema = @Schema(implementation = QueryDto.class))))})
    @GetMapping
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok().body("OK");
    }
}
