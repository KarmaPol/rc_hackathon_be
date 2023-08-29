package com.rch.rch_backend.domain.employPosting.model;

import com.rch.rch_backend.domain.posting_like.PostingLike;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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


    @OneToMany(mappedBy = "employPosting")
    private List<PostingLike> postingLikes;

}

