package com.rch.rch_backend.domain.employPosting.model;

import com.rch.rch_backend.domain.posting_like.PostingLike;
import com.rch.rch_backend.domain.user.model.CompanyUser;
import com.rch.rch_backend.domain.user.model.Users;
import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployPosting {

    @Id
    @Column(name = "postingId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "postingName")
    private String postingName;

    @Column(name = "region")
    private String region;

    @Column(name = "jobgroup")
    private String jobGroup;

    @Column(name = "content")
    private String content;

    @Column(name = "techstack")
    private String techStack;

    @Column(name = "wage")
    private Long wage;

    @Column(name = "deadline")
    private LocalDateTime deadLine;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래키를 설정
    private CompanyUser companyUser;

    @OneToMany(mappedBy = "employPosting")
    private List<PostingLike> postingLikes;

}

