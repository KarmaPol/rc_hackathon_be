package com.rch.rch_backend.domain.employPosting.model;

import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.posting_like.model.PostingLike;
import com.rch.rch_backend.domain.user.model.CompanyUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmployPosting {

    @Id
    @Column(name = "posting-id")
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
    private Collection<PostingLike> postingLikes;

    @Builder
    public EmployPosting(Long id, String postingName, String region, String jobGroup, String content, String techStack, Long wage, LocalDateTime deadLine, CompanyUser companyUser, List<PostingLike> postingLikes) {
        this.id = id;
        this.postingName = postingName;
        this.region = region;
        this.jobGroup = jobGroup;
        this.content = content;
        this.techStack = techStack;
        this.wage = wage;
        this.deadLine = deadLine;
        this.companyUser = companyUser;
        this.postingLikes = postingLikes;
    }

    public void updateFromDto(EmployPostingRequestDto updatedDto) {
        this.postingName = updatedDto.getPostingName();
        this.region = updatedDto.getRegion();
        this.jobGroup = updatedDto.getJobGroup();
        this.content = updatedDto.getContent();
        this.techStack = updatedDto.getTechStack();
        this.wage = updatedDto.getWage();
        this.deadLine = updatedDto.getDeadLine();
    }
}

