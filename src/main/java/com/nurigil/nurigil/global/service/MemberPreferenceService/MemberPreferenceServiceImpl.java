package com.nurigil.nurigil.global.service.MemberPreferenceService;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.handler.MemberHandler;
import com.nurigil.nurigil.global.apiPayload.exception.handler.MemberPrefenceHandler;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.MemberPreference;
import com.nurigil.nurigil.global.repository.MemberPreferenceRepository;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceRequestDTO;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPreferenceServiceImpl implements MemberPreferenceService{

    private final MemberRepository memberRepository;
    private final MemberPreferenceRepository memberPreferenceRepository;

    // 선호 설정 등록 API
    @Override
    public MemberPreferenceResponseDTO.MemberPreferenceResponse createMemberPreference(Long memberId, MemberPreferenceRequestDTO.MemberPreferenceRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_ID_NULL));

        // 이미 선호 설정이 있는 경우 덮어쓰기 방지 or 예외 처리
        if (member.getMemberPreference() != null) {
            throw new MemberPrefenceHandler(ErrorStatus.MEMBER_PREFERENCE_ALREADY_EXISTS);
        }

        MemberPreference memberPreference = MemberPreference.builder()
                .member(member)
                .difficulty(request.getDifficulty())
                .slope(request.getSlope())
                .build();

        memberPreferenceRepository.save(memberPreference);

        return MemberPreferenceResponseDTO.MemberPreferenceResponse.builder()
                .difficulty(memberPreference.getDifficulty())
                .slope(memberPreference.getSlope())
                .build();
    }
}
