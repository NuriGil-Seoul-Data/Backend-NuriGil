package com.nurigil.nurigil.global.service.MemberPreferenceService;

import com.nurigil.nurigil.global.apiPayload.code.status.ErrorStatus;
import com.nurigil.nurigil.global.apiPayload.exception.handler.MemberHandler;
import com.nurigil.nurigil.global.apiPayload.exception.handler.MemberPrefenceHandler;
import com.nurigil.nurigil.global.converter.MemberPreferenceConverter;
import com.nurigil.nurigil.global.domain.entity.Member;
import com.nurigil.nurigil.global.domain.entity.MemberPreference;
import com.nurigil.nurigil.global.repository.MemberPreferenceRepository;
import com.nurigil.nurigil.global.repository.MemberRepository;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceRequestDTO;
import com.nurigil.nurigil.global.web.dto.MemberPreference.MemberPreferenceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberPreferenceServiceImpl implements MemberPreferenceService{

    private final MemberRepository memberRepository;
    private final MemberPreferenceRepository memberPreferenceRepository;

    // 선호 설정 등록 API
    @Override
    @Transactional
    public MemberPreferenceResponseDTO.Response createMemberPreference(Long memberId, MemberPreferenceRequestDTO.Request request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_ID_NULL));

        // 이미 선호 설정이 있는 경우 덮어쓰기 방지 or 예외 처리
        if (member.getMemberPreference() != null) {
            throw new MemberPrefenceHandler(ErrorStatus.MEMBER_PREFERENCE_ALREADY_EXISTS);
        }

        MemberPreference newMemberPreference = MemberPreferenceConverter.toMemberPreference(request);
        newMemberPreference.setMember(member);

        memberPreferenceRepository.save(newMemberPreference);

        return MemberPreferenceConverter.toCreateResultDTO(newMemberPreference);
    }

    // 선호 설정 조회 API
    @Override
    public MemberPreferenceResponseDTO.Response getMemberPreference(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_ID_NULL));

        MemberPreference memberPreference = memberPreferenceRepository
                .findByMember(member)
                .orElseThrow(() -> new MemberPrefenceHandler(ErrorStatus.MEMBER_PREFERENCE_NULL));

        return MemberPreferenceConverter.toCreateResultDTO(memberPreference);
    }

    // 선호 설정 수정 API
    @Override
    @Transactional
    public MemberPreferenceResponseDTO.Response PatchMemberPreference(Long memberId, MemberPreferenceRequestDTO.Request request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_ID_NULL));

        Optional<MemberPreference> optionalMemberPreference = memberPreferenceRepository.findByMember(member);

        // 선호 설정이 없으면 request로 들어온 요청을 선호 설정으로 등록
        if (optionalMemberPreference.isEmpty()) {
            MemberPreference newMemberPreference = MemberPreferenceConverter.toMemberPreference(request);
            newMemberPreference.setMember(member);
            memberPreferenceRepository.save(newMemberPreference);
            return MemberPreferenceConverter.toCreateResultDTO(newMemberPreference);
        } else { // 선호 설정이 있는 경우 update
            MemberPreference existMemberPreference = optionalMemberPreference.get();
            existMemberPreference.updateMemberPreference(request.getDifficulty(), request.getSlope());
            memberPreferenceRepository.save(existMemberPreference);
            return MemberPreferenceConverter.toCreateResultDTO(existMemberPreference);
        }
    }
}
