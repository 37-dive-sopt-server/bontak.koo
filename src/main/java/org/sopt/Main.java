package org.sopt;

import org.sopt.domain.member.controller.MemberController;
import org.sopt.domain.member.dto.req.CreateMemberReq;
import org.sopt.domain.member.entity.Gender;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.repository.MemoryMemberRepository;
import org.sopt.domain.member.service.MemberServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        MemberServiceImpl memberService = new MemberServiceImpl();
        MemberController memberController = new MemberController();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n✨ --- DIVE SOPT 회원 관리 서비스 --- ✨");
            System.out.println("---------------------------------");
            System.out.println("1️⃣. 회원 등록 ➕");
            System.out.println("2️⃣. ID로 회원 조회 🔍");
            System.out.println("3️⃣. 전체 회원 조회 📋");
            System.out.println("4️⃣. 회원 삭제");
            System.out.println("0. 종료 🚪");
            System.out.println("---------------------------------");
            System.out.print("메뉴를 선택하세요: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    try {
                        System.out.print("등록할 회원 이름을 입력하세요: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("⚠️ 이름을 입력해주세요.");
                            break;
                        }

                        System.out.print("등록할 생년월일(YYYY-MM-DD)을 입력하세요: ");
                        LocalDate birthday = LocalDate.parse(scanner.nextLine());

                        System.out.print("등록할 이메일을 입력하세요: ");
                        String email = scanner.nextLine().trim();
                        if (email.isEmpty()) {
                            System.out.println("⚠️ 이메일을 입력해주세요.");
                            break;
                        }

                        System.out.print("등록할 성별(MALE/FEMALE)을 입력하세요: ");
                        Gender gender = Gender.valueOf(scanner.nextLine().trim().toUpperCase());

                        // ✅ CreateMemberReq DTO 생성
                        CreateMemberReq createMemberReq = new CreateMemberReq(name, birthday, email, gender);

                        // ✅ Controller 호출
                        Long createdId = memberController.createMember(createMemberReq);

                        if (createdId != null) {
                            System.out.println("✅ 회원 등록 완료 (ID: " + createdId + ")");
                        } else {
                            System.out.println("❌ 회원 등록 실패");
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ 성별은 MALE 또는 FEMALE만 입력 가능합니다.");
                    } catch (DateTimeParseException e) {
                        System.out.println("❌ 생년월일 형식이 잘못되었습니다. (예: 2000-01-01)");
                    } catch (Exception e) {
                        System.out.println("❌ 등록 중 오류가 발생했습니다: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.print("조회할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        Optional<Member> foundMember = memberController.findMemberById(id);
                        if (foundMember.isPresent()) {
                            System.out.println("✅ 조회된 회원: ID=" + foundMember.get().getId() + ", 이름=" + foundMember.get().getName());
                        } else {
                            System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    }
                    break;
                case "3":
                    List<Member> allMembers = memberController.getAllMembers();
                    if (allMembers.isEmpty()) {
                        System.out.println("ℹ️ 등록된 회원이 없습니다.");
                    }
                    else {
                        System.out.println("--- 📋 전체 회원 목록 📋 ---");
                        for (Member member : allMembers) {
                            System.out.println("👤 ID=" + member.getId() + ", 이름=" + member.getName());
                        }
                        System.out.println("--------------------------");
                    }
                    break;
                case "4":
                    System.out.print("삭제할 회원 ID를 입력하세요: ");
                    try {
                        Long id = Long.parseLong(scanner.nextLine());
                        boolean deleted = memberController.deleteMemberById(id);
                        if (deleted) {
                            System.out.println("🗑️ 회원이 성공적으로 삭제되었습니다. (ID=" + id + ")");
                        } else {
                            System.out.println("⚠️ 해당 ID의 회원을 찾을 수 없습니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 유효하지 않은 ID 형식입니다. 숫자를 입력해주세요.");
                    }
                    break;
                case "0":
                    System.out.println("👋 서비스를 종료합니다. 안녕히 계세요!");
                    scanner.close();
                    return;
                default:
                    System.out.println("🚫 잘못된 메뉴 선택입니다. 다시 시도해주세요.");
            }
        }
    }
}
