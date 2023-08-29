package com.rch.rch_backend.domain.employPosting.service;

import com.rch.rch_backend.domain.employPosting.dto.ReqEmployPosting;
import com.rch.rch_backend.domain.employPosting.dto.RespEmployPosting;
import org.springframework.stereotype.Service;

@Service
public class EmployPostingServiceImpl implements EmployPositngService {

    @Override
    public RespEmployPosting.Info createEmployPosting(ReqEmployPosting.Create createDto) {
        return null;
    }

    @Override
    public RespEmployPosting.Info updateEmployPositng(Long postingId, ReqEmployPosting.Update updatedDto) {
        return null;
    }

    @Override
    public void deletePost(Long postingId) {

    }
}
