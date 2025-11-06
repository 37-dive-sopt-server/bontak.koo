package org.sopt.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.dto.res.AllMembersRes;
import org.sopt.domain.member.dto.res.MemberIdRes;
import org.sopt.domain.member.dto.res.MemberRes;
import org.sopt.domain.member.service.MemberService;
import org.sopt.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member", description = "멤버 관련 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "멤버 생성",
            description = "멤버를 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<MemberIdRes>> createMember(@RequestBody CreateMemberReq createMemberReq) {
        MemberIdRes response = memberService.join(createMemberReq);
        return ResponseEntity.ok(ApiResponse.created(response));
    }

    @Operation(summary = "특정 멤버 조회",
            description = "멤버id로 특정 멤버를 조회합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<MemberRes>> findMemberById(@PathVariable("userId") Long id) {
        MemberRes response = memberService.findOne(id);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @Operation(summary = "모든 멤버 조회",
            description = "전체 멤버를 조회합니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<AllMembersRes>> getAllMembers() {
        AllMembersRes response = memberService.findAllMembers();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @Operation(summary = "특정 멤버 삭제",
            description = "멤버id로 특정 멤버를 삭제합니다.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<MemberIdRes>> deleteMemberById(@PathVariable("userId") Long id) {
        MemberIdRes response = memberService.deleteMember(id);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
