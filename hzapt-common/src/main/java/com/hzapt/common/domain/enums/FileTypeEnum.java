package com.hzapt.common.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类型:1-文件，2-图片，3-视频，4-语音，5-文档
 * @author LIJIAN
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    FILE("文件", 1), IMG("图片", 2), VIDEO("视频", 3), VOICE("语音", 4), DOCUMENT("文档", 5);

    /**
     * 描述
     */
    private String describe;
    /**
     * 值
     */
    private Integer value;

    public static String getDescribe(Integer value) {
        for (FileTypeEnum p : FileTypeEnum.values()) {
            if (p.getValue().equals(value)) {
                return p.describe;
            }
        }
        return null;
    }

}
