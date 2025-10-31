package org.sopt.domain.member.controller;

import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.dto.res.AllMembersRes;
import org.sopt.domain.member.dto.res.MemberIdRes;
import org.sopt.domain.member.dto.res.MemberRes;
import org.sopt.domain.member.service.MemberService;
import org.sopt.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MemberIdRes>> createMember(@RequestBody CreateMemberReq createMemberReq) {
        MemberIdRes response = memberService.join(createMemberReq);
        return ResponseEntity.ok(ApiResponse.created(response));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<MemberRes>> findMemberById(@PathVariable("userId") Long id) {
        MemberRes response = memberService.findOne(id);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<AllMembersRes>> getAllMembers() {
        AllMembersRes response = memberService.findAllMembers();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<MemberIdRes>> deleteMemberById(@PathVariable("userId") Long id) {
        MemberIdRes response = memberService.deleteMember(id);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
