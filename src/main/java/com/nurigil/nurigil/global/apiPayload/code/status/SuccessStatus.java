package com.nurigil.nurigil.global.apiPayload.code.status;

import com.nurigil.nurigil.global.apiPayload.code.BaseCode;
import com.nurigil.nurigil.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    USER_LOGIN_OK(HttpStatus.OK, "AUTH2001", "회원 로그인이 완료되었습니다."),

    // Member 관련 응답
    MEMBER_SIGNUP_OK(HttpStatus.OK, "MEMBER_2001", "회원가입이 완료되었습니다."),
    MEMBER_LOGIN_OK(HttpStatus.OK, "MEMBER_2002", "회원 로그인이 완료되었습니다."),
    MEMBER_LOGOUT_OK(HttpStatus.OK, "MEMBER_2003", "회원 로그아웃이 완료되었습니다."),
    MEMBER_INFO_GET_OK(HttpStatus.OK, "MEMBER_2004", "사용자 정보 조회가 완료되었습니다."),
    MEMBER_INFO_PATCH_OK(HttpStatus.OK, "MEMBER_2005", "사용자 정보 수정이 완료되었습니다."),
    MEMBER_DELETE_OK(HttpStatus.OK, "MEMBER_2006", "회원 탈퇴가 완료되었습니다."),

    // MemberPreference 관련 응답
    MEMBER_PREFERENCE_POST_OK(HttpStatus.OK, "MEMBER_PREFERENCE_2001", "사용자 선호 설정 등록이 완료되었습니다."),
    MEMBER_PREFERENCE_GET_OK(HttpStatus.OK, "MEMBER_PREFERENCE_2002", "사용자 선호 설정 조회가 완료되었습니다."),
    MEMBER_PREFERENCE_PATCH_OK(HttpStatus.OK, "MEMBER_PREFERENCE_2003", "사용자 선호 설정 수정이 완료되었습니다."),

    // Course 관련 응답
    COURSE_ALL_OK(HttpStatus.OK, "COURSE_2001", "전체 코스 조회가 완료되었습니다."),
    COURSE_DETAIL_OK(HttpStatus.OK, "COURSE_2002", "특정 코스 상세 조회가 완료되었습니다."),
    COURSE_DELETE_OK(HttpStatus.OK, "COURSE_2003", "특정 코스 삭제가 완료되었습니다."),

    // CourseAmenity 관련 응답
    COURSE_AMENITY_OK(HttpStatus.OK, "COURSE_AMENITY_2001", "특정 코스의 편의시설 조회가 완료되었습니다."),

    // MemberCourse 관련 응답
    MEMBER_COURSE_START_OK(HttpStatus.OK, "MEMBER_COURSE_2001", "코스 시작이 완료되었습니다."),
    MEMBER_COURSE_FINISH_OK(HttpStatus.OK, "MEMBER_COURSE_2002", "코스 종료가 완료되었습니다."),
    MEMBER_COURSE_GET_OK(HttpStatus.OK, "MEMBER_COURSE_2003", "코스 목록 조회가 완료되었습니다."),
    MEMBER_COURSE_DETAIL_OK(HttpStatus.OK, "MEMBER_COURSE_2004", "특정 코스 기록 조회가 완료되었습니다."),
    MEMBER_COURSE_DELETE_OK(HttpStatus.OK, "MEMBER_COURSE_2005", "코스 저장 취소가 완료되었습니다."),

    // PathReport 관련 응답
    PATH_REPORT_POST_OK(HttpStatus.OK, "PATH_REPORT_2001", "신고 생성이 완료되었습니다."),
    PATH_REPORT_GET_OK(HttpStatus.OK, "PATH_REPORT_2002", "신고 목록 조회가 완료되었습니다."),

    // WalkCalendar 관련 응답
    WALK_CALENDAR_OK(HttpStatus.OK, "WALK_CALENDAR_2001", "산책 달력 데이터 전체 조회가 완료되었습니다."),
    WALK_CALENDAR_DETAIL_OK(HttpStatus.OK, "WALK_CALENDAR_2001", "특정 날짜 산책 기록 조회가 완료되었습니다."),

    // EmergencyCall 관련 응답
    EMERGENCY_CALL_OK(HttpStatus.OK, "EMERGENCY_CALL_2001", "긴급 호출 요청이 완료되었습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder().message(message).code(code).isSuccess(true).build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}
