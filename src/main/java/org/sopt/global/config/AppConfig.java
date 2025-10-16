package org.sopt.global.config;

import org.sopt.domain.member.controller.MemberController;
import org.sopt.domain.member.repository.FileMemberRepositoryImpl;
import org.sopt.domain.member.repository.MemberRepository;
import org.sopt.domain.member.repository.flush.SmartFlushManager;
import org.sopt.domain.member.repository.storage.FileStorage;
import org.sopt.domain.member.service.MemberService;
import org.sopt.domain.member.service.MemberServiceImpl;
import org.sopt.domain.member.validator.AgeValidator;
import org.sopt.domain.member.validator.EmailValidator;

public class AppConfig {

    public MemberController memberController() {
        return new MemberController(memberService());
    }

    public MemberService memberService() {
        return new MemberServiceImpl(
                memberRepository(),
                emailValidator(),
                ageValidator()
        );
    }

    private static final String MEMBER_FILE_PATH = "./members.txt";
    private static final long FLUSH_INTERVAL_SECONDS = 10;

    public MemberRepository memberRepository() {
        return new FileMemberRepositoryImpl(fileStorage(), smartFlushManager());
    }

    public FileStorage fileStorage() {
        return new FileStorage(MEMBER_FILE_PATH);
    }

    public SmartFlushManager smartFlushManager() {
        return new SmartFlushManager(FLUSH_INTERVAL_SECONDS);
    }

    public EmailValidator emailValidator() {
        return new EmailValidator(memberRepository());
    }

    public AgeValidator ageValidator() {
        return new AgeValidator();
    }
}
