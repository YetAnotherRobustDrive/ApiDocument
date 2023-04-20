package com.example.demo.controller;

import com.example.demo.ErrorDto;
import com.example.demo.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/directories")
public class DirectoriesController {
    @Getter
    public static class DirectoryDto {
        private Long id;
        private String name;
        private boolean isShared;
    }

    @Getter
    public static class FileDto {
        private Long id;
        private SecurityLevel securityLevel;
        private WritingStage writingStage;
        private boolean isShared;
        private String name;
        private Long sie;
        private String type;
        private String thumbnail;

        private enum SecurityLevel {
            CONFIDENTIAL, PUBLIC, SENSITIVE
        }

        private enum WritingStage {
            DRAFT, EXPIRED, FINAL
        }
    }

    @Getter
    public static class DirectoryDetailsDto {
        private Long id;
        private String name;
        private Long authorId;
        private Date createTime;
        private boolean isShared;
    }

    @Operation(summary = "디렉토리 정보 조회", description = "특정 디렉토리의 자세한 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 디렉토리 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = DirectoryDetailsDto.class))),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content)})
    @GetMapping("/{directoryId}")
    public ResponseEntity<?> read(@PathVariable String directoryId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 안에 파일들 조회", description = "특정 디렉토리 안의 파일정보의 리스트를 조회합니다.")
    @Parameters({
            @Parameter(name = "order", description = "어떤 기준으로 정렬할 것인지 기본값은 name", schema = @Schema(implementation = SortType.class)),
            @Parameter(name = "offset", description = "페이지네이션에서 몇번째 페이지를 요청하는지 기본값은 0"),
            @Parameter(name = "desc", description = "내림차순으로 정렬할 것인지 기본값은 false", schema = @Schema(implementation = Boolean.class))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 디렉토리의 파일들 정보 조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileDto.class)))),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content)})
    @GetMapping("/{directoryId}/files")
    public ResponseEntity<?> readFiles(@PathVariable String directoryId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 안에 디렉토리들 조회", description = "특정 디렉토리 안의 디렉토리정보의 리스트를 조회합니다.")
    @Parameters({
            @Parameter(name = "order", description = "어떤 기준으로 정렬할 것인지 기본값은 name", schema = @Schema(implementation = SortType.class)),
            @Parameter(name = "offset", description = "페이지네이션에서 몇번째 페이지를 요청하는지 기본값은 0"),
            @Parameter(name = "desc", description = "내림차순으로 정렬할 것인지 기본값은 false", schema = @Schema(implementation = Boolean.class))
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 디렉토리의 파일들 정보 조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileDto.class)))),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @GetMapping("/{directoryId}/subDirectories")
    public ResponseEntity<?> readSubDirectories(@PathVariable String directoryId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 안에 디렉토리를 생성", description = "특정 디렉토리 안의 디렉토리를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "디렉토리 생성 성공", content = @Content),
            @ApiResponse(responseCode = "403", description = "디렉토리 이름이 중복됨", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @PostMapping("/{directoryId}/create")
    public ResponseEntity<?> create(@PathVariable String directoryId, @RequestParam String fileName) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 삭제", description = "특정 디렉토리를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "디렉토리 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @RequestMapping(value = "/{directoryId}/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> delete(@PathVariable String directoryId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 복원", description = "특정 디렉토리를 복원합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "디렉토리 복원 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @RequestMapping(value = "/{directoryId}/restore", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> restore(@PathVariable String directoryId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 이름 변경", description = "특정 디렉토리의 이름을 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "디렉토리 이름 변경 성공", content = @Content),
            @ApiResponse(responseCode = "403", description = "디렉토리 이름이 중복됨", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @PostMapping("/{directoryId}/restore")
    public ResponseEntity<?> rename(@PathVariable String directoryId, @RequestParam String name) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 이동", description = "특정 디렉토리를 다른 디렉토리로 이동합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "디렉토리 이름 변경 성공", content = @Content),
            @ApiResponse(responseCode = "403", description = "디렉토리 이름이 중복됨", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @PostMapping("/{directoryId}/move")
    public ResponseEntity<?> move(@PathVariable String directoryId, @RequestParam Long targetDirectoryId) {
        return ResponseEntity.ok().body("OK");
    }

    @Operation(summary = "특정 디렉토리 삭제", description = "특정 디렉토리를 완전히 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "디렉토리 삭제 성공", content = @Content),
            @ApiResponse(responseCode = "404", description = "특정 디렉토리를 찾을 수 없음", content = @Content(schema = @Schema(implementation = ErrorDto.class)))})
    @RequestMapping(value = "/{directoryId}/purge", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> purge(@PathVariable String directoryId) {
        return ResponseEntity.ok().body("OK");
    }
}
