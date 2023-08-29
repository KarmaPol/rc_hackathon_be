package com.rch.rch_backend.domain.employPosting.service;

import com.rch.rch_backend.domain.employPosting.dto.ReqEmployPosting;
import com.rch.rch_backend.domain.employPosting.dto.RespEmployPosting;

public interface EmployPositngService {

    RespEmployPosting.Info createEmployPosting(ReqEmployPosting.Create createDto);
    RespEmployPosting.Info updateEmployPositng(Long postingId, ReqEmployPosting.Update updatedDto);
    void deletePost(Long postingId);
}
