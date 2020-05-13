package com.wy.config.wechat;

public interface WecahtCode {
    /**
     * 系统繁忙，此时请开发者稍候再试
     */
    final int BUSY = -1;
    /**
     * 请求成功
     */
    final int SUCCESS = 0;
    /**
     * code无效
     */
    final int ERROR_CODE = 40029;
    /**
     * 频率限制，每个用户每分钟100次
     */
    final int TOO_FREQUENT = 45011;
}
