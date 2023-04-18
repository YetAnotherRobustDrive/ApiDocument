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
@RequestMapping("/notifications")
public class NotificationsController {
    @Operation(summary = "알림 봄", description = "유저가 알림을 보았다면, 본 것으로 처리함")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "알림을 보았다고 설정됨", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 알림이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("/{notificationId}/seen")
    public ResponseEntity<?> seen(@PathVariable Long notificationId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "알림 확인", description = "알림들의 정보를 리턴함")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "알림들을 리턴함",
                    content = @Content(array = @ArraySchema(
                            schema = @Schema(implementation = NotificationDto.class))))
    })
    @GetMapping
    public ResponseEntity<?> read() {
        return ResponseEntity.ok().body("OK");
    }

    @Getter
    private static class NotificationDto {
        private Long id;
        private String message;
        private boolean seen;
    }

    @Operation(summary = "알림 삭제", description = "특정 알림을 삭제함")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "알림 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "알림이 존재하지 않음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @RequestMapping(value = "/{notification_id}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> delete(@PathVariable String notification_id) {
        return ResponseEntity.ok().body("OK");
    }
}
