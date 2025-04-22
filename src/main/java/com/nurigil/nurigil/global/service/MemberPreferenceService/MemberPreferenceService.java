package com.nurigil.nurigil.global.service.MemberPreferenceService;

import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceRequestDTO;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceResponseDTO;

public interface MemberPreferenceService {
    MemberPreferenceResponseDTO.Response createMemberPreference(Long memberId, MemberPreferenceRequestDTO.Request request);
    MemberPreferenceResponseDTO.Response getMemberPreference(Long memberId);
    MemberPreferenceResponseDTO.Response PatchMemberPreference(Long memberId, MemberPreferenceRequestDTO.Request request);
}
