package com.nurigil.nurigil.global.apiPayload.code.status;

import com.nurigil.nurigil.global.apiPayload.code.BaseErrorCode;
import com.nurigil.nurigil.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 기본 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 공통 에러
    PAGE_UNDER_ZERO(HttpStatus.BAD_REQUEST, "COMM_001", "페이지는 0이상이어야 합니다."),

    // S3 관련
    S3_OBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "S3_001", "S3 오브젝트를 찾을 수 없습니다."),
    S3_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "S3_002", "S3 업로드 실패"),
    S3_EMPTY_FILE_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "S3_003", "파일이 존재하지 않습니다."),
    S3_DELETE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "S3_004", "S3 삭제 실패"),

    // Auth 관련
    AUTH_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_001", "토큰이 만료되었습니다."),
    AUTH_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_002", "토큰이 유효하지 않습니다."),
    INVALID_LOGIN_REQUEST(HttpStatus.UNAUTHORIZED, "AUTH_003", "올바른 아이디나 패스워드가 아닙니다."),
    NOT_EQUAL_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_004", "리프레시 토큰이 다릅니다."),
    NOT_CONTAIN_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_005", "해당하는 토큰이 저장되어있지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH_006", "비밀번호가 일치하지 않습니다."),
    INVALID_REQUEST_INFO(HttpStatus.UNAUTHORIZED, "AUTH_007", "카카오 정보 불러오기에 실패하였습니다."),
    AUTH_INVALID_CODE(HttpStatus.UNAUTHORIZED, "AUTH_008", "코드가 유효하지 않습니다."),
    ILLEGAL_REGISTRATION_ID(HttpStatus.BAD_REQUEST, "AUTH_009", "허용되지 않은 소셜 로그인 경로입니다."),
    INVALID_LOGIN_EMAIL(HttpStatus.UNAUTHORIZED, "AUTH_010", "존재하지 않는 이메일입니다."),


    // Member 관련
    MEMBER_ID_NULL(HttpStatus.BAD_REQUEST, "MEMBER_4001", "사용자 아이디는 필수 입니다."),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER_4002", "사용자가 없습니다."),
    MEMBER_NAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER_4003", "이름입력은 필수 입니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_4091", "이미 존재하는 유저입니다."),
    MEMBER_ADMIN_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "MEMBER_4004", "관리자 권한이 없습니다."),
    MEMBER_LOGIN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER_4005", "아이디나 비밀번호가 올바르지 않습니다."),
    MEMBER_WRONG_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER_4006", "이메일 형식이 올바르지 않습니다."),
    MEMBER_WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER_4007", "비밀번호 형식이 올바르지 않습니다."),
    MEMBER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_4092", "이미 가입된 이메일입니다."),
    MEMBER_NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_4093", "이미 존재하는 닉네임입니다."),

    // MemberPreference 관련
    MEMBER_PREFERENCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBERPREFERENCE_4091", "이미 선호 설정이 존재합니다."),
    MEMBER_PREFERENCE_NULL(HttpStatus.NOT_FOUND, "MEMBERPREFERENCE_4041", "선호 설정이 존재하지 않습니다."),

    //Search 관련
    SEARCH_CONDITION_INVALID(HttpStatus.BAD_REQUEST, "SEARCH_001", "검색 조건이 하나라도 존재해야 합니다."),
    RECREATION_NOT_FOUND(HttpStatus.NOT_FOUND, "SEARCH_001", "검색 결과가 존재하지 않습니다."),

    // Calendar 관련
    CALENDAR_NOT_FOUND(HttpStatus.NOT_FOUND, "CALENDAR_001", "산책 달력 내용이 없습니다."),

    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}