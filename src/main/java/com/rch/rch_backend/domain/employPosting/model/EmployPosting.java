package com.rch.rch_backend.domain.employPosting.model;

import com.rch.rch_backend.domain.common.BaseEntity;
import com.rch.rch_backend.domain.employPosting.dto.EmployPostingRequestDto;
import com.rch.rch_backend.domain.posting_like.model.PostingLike;
import com.rch.rch_backend.domain.user.model.CompanyUser;
import com.rch.rch_backend.domain.user.model.Users;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployPosting extends BaseEntity {

    @Id
    @Column(name = "posting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postingName;
    private String region;
    private String jobGroup;
    private String content;
    private String techStack;
    private Long wage;
    private LocalDateTime deadLine;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래키를 설정
    private Users user;

    @OneToMany(mappedBy = "employPosting")
    private List<PostingLike> postingLikes;


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

