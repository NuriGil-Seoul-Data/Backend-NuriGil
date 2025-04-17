package com.nurigil.nurigil.global.web.dto.MemberPreference;

import com.nurigil.nurigil.global.domain.enums.Difficulty;
import com.nurigil.nurigil.global.domain.enums.Slope;
import lombok.*;

public class MemberPreferenceResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberPreferenceResponse {
        private Difficulty difficulty;
        private Slope slope;
    }
}
