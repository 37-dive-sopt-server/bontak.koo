package org.sopt.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.sopt.domain.article.entity.Article;

import java.time.LocalDate;
import java.util.List;

@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE members SET is_deleted = true WHERE id = ?")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "members")
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Article> articles;

    private String name;
    private LocalDate birthday;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // soft delete
    @JsonIgnore
    private boolean isDeleted = Boolean.FALSE;

    public Member(Long id, String name, LocalDate birthday, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
    }

    public static Member of(String name, LocalDate birthday, String email, Gender gender) {
        return Member.builder()
                .name(name)
                .birthday(birthday)
                .email(email)
                .gender(gender)
                .build();
    }
}
