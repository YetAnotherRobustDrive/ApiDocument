package com.example.demo.controller;

import com.example.demo.ErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FilesController {
    // crud, move, details, restore, rename

    @Operation(summary = "파일의 구체적인 정보를 가져옵니다.", description = "볼 권한이 있는 유저가 접근할 수 있습니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 정보 가져오기 성공",
                    content = @Content(schema = @Schema(implementation = FileDto.class))),
            @ApiResponse(responseCode = "404", description = "파일이 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("/{fileId}/details")
    public ResponseEntity<?> details(@PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "파일을 만듭니다.", description = "파일을 만듭니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 생성 성공",
                    content = @Content(schema = @Schema(implementation = FileDto.class))),
            @ApiResponse(responseCode = "403", description = "파일 이름 중복",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "잘못된 이름(슬래쉬나 역슬래쉬가 들어감)",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "상위 디렉토리에 접근할 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "용량이 부족합니다.",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping
    public ResponseEntity<?> create(FileCreateDto fileCreateDto) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "파일을 다운로드 합니다.", description = "파일을 읽을 권한이 있다면 다운로드 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 정보 가져오기 성공",
                    content = @Content(schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "404", description = "파일이 존재하지 않음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @GetMapping("/{fileId}")
    public ResponseEntity<?> download(@PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "파일을 삭제 합니다.", description = "파일을 읽을 권한이 있다면 파일을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @RequestMapping(value = "/{fileId}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> delete(@PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "파일을 복원합니다.", description = "파일을 읽을 권한이 있다면 파일을 복원합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 복원 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @RequestMapping(value = "/{fileId}/restore", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> restore(@PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "파일의 이름을 바꿉니다.", description = "파일을 읽을 권한이 있다면 파일 이름을 바꿉니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 이름 바꾸기 성공",
                    content = @Content(schema = @Schema(implementation = FileDto.class))),
            @ApiResponse(responseCode = "403", description = "파일 이름 중복",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "403", description = "잘못된 이름(슬래쉬나 역슬래쉬가 들어감)",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping(value = "/{fileId}/rename")
    public ResponseEntity<?> rename(@RequestParam String newName, @PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "라벨을 업데이트 합니다.", description = "파일을 볼 권한이 있다면, 입력받은 라벨들로 파일의 라벨을 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "라벨 업데이트를 성공했습니다.", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @PostMapping("/{fileId}/update/label")
    public ResponseEntity<?> updateLabel(@RequestParam List<String> labels, @PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "보안 레벨을 업데이트 합니다.", description = "파일을 볼 권한이 있다면, 보안 레벨을 업데이트 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "보안등급 변경에 성공했습니다.", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @PostMapping(value = "/{fileId}/update/security-level")
    public ResponseEntity<?> updateSecurityLevel(@RequestParam FileDto.SecurityLevel securityLevel, @PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "파일을 작성단계를 업데이트합니다.", description = "파일을 볼 권한이 있다면, 작성단계를 업데이트 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 작성단계 업데이트 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @PostMapping("/{fileId}/update/writing-stage")
    public ResponseEntity<?> updateWritingStage(@RequestParam FileDto.WritingStage writingStage, @PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "파일을 옮깁니다.", description = "파일을 볼 권한이 있다면, 파일 위치를 옮깁니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 옮기기 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음",
                content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "파일 이름이 중복됨",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @PostMapping("/{fileId}/move")
    public ResponseEntity<?> move(@RequestParam Long targetDirectoryId, @PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }


    @Operation(summary = "파일을 완전삭제합니다.", description = "파일을 볼 권한이 있다면, 파일을 완전삭제 합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파일 완전삭제 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 파일을 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorDto.class)))
    })
    @RequestMapping(value = "/{fileId}/purge", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> purge(@PathVariable String fileId) {
        return ResponseEntity.ok().body("OK");
    }
    @Getter
    private static class FileDto {
        private Long id;
        private String name;
        private Long author;
        private Date createTime;
        private Date accessTime;
        private List<LabelDto> labels; // user가 만든 라벨만 가져온다.
        private Long size;
        private String type;
        private boolean isShared;
        private boolean isEncrypted;
        private SecurityLevel securityLevel;
        private WritingStage writingStage;

        private static class LabelDto {
            private Long id;
            private String name;
        }
        private enum SecurityLevel {
            CONFIDENTIAL, PUBLIC, SENSITIVE
        }

        private enum WritingStage {
            DRAFT, EXPIRED, FINAL
        }
    }

    private static class FileCreateDto {
        MultipartFile file;
        private String name;
        private List<FileDto.LabelDto> labels;
        private boolean isEncrypted;
    }
}
