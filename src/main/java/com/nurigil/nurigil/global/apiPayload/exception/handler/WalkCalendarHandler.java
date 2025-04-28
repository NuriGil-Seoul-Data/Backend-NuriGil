package com.nurigil.nurigil.global.apiPayload.exception.handler;

import com.nurigil.nurigil.global.apiPayload.code.BaseCode;
import com.nurigil.nurigil.global.apiPayload.code.BaseErrorCode;
import com.nurigil.nurigil.global.apiPayload.exception.GeneralException;

public class WalkCalendarHandler extends GeneralException {
    public WalkCalendarHandler(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
