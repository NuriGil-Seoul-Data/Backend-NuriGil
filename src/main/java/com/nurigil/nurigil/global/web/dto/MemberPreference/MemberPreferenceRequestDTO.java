package com.nurigil.nurigil.global.web.dto.MemberPreference;

import com.nurigil.nurigil.global.domain.enums.Difficulty;
import com.nurigil.nurigil.global.domain.enums.Slope;
import lombok.*;

public class MemberPreferenceRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberPreferenceRequest {
        private Difficulty difficulty;
        private Slope slope;
    }
}
