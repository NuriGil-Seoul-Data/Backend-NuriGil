package com.nurigil.nurigil.global.apiPayload.exception.handler;

import com.nurigil.nurigil.global.apiPayload.code.BaseErrorCode;
import com.nurigil.nurigil.global.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
