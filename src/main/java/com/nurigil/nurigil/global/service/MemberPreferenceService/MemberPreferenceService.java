package com.nurigil.nurigil.global.service.MemberPreferenceService;

import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceRequestDTO;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceResponseDTO;

public interface MemberPreferenceService {
    MemberPreferenceResponseDTO.MemberPreferenceResponse createMemberPreference(Long memberId, MemberPreferenceRequestDTO.MemberPreferenceRequest request);
}
