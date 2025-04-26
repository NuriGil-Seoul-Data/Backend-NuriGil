package com.nurigil.nurigil.global.converter;

import com.nurigil.nurigil.global.domain.entity.MemberPreference;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceRequestDTO;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceResponseDTO;

public class MemberPreferenceConverter {

    public static MemberPreference toMemberPreference(MemberPreferenceRequestDTO.Request request) {
        return MemberPreference.builder()
                .difficulty(request.getDifficulty())
                .slope(request.getSlope())
                .build();
    }

    // 선호 내용을 갖는 DTO로 변경
    public static MemberPreferenceResponseDTO.Response toCreateResultDTO(MemberPreference memberPreference) {
        return MemberPreferenceResponseDTO.Response.builder()
                .difficulty(memberPreference.getDifficulty())
                .slope(memberPreference.getSlope())
                .build();
    }
}
