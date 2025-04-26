package com.nurigil.nurigil.global.apiPayload.exception.handler;

import com.nurigil.nurigil.global.apiPayload.code.BaseErrorCode;
import com.nurigil.nurigil.global.apiPayload.exception.GeneralException;

public class MemberPrefenceHandler extends GeneralException {
    public MemberPrefenceHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
