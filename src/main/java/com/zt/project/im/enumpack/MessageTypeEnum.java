package com.zt.project.im.enumpack;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 13:03 2018/4/14
 */
public enum MessageTypeEnum {
    TEXT_MESSAGE("文本",1), IMAGE_MESSAGE("image",2), VOICE_MESSAGE("语音",3);

    private String type;

    private int index;

    MessageTypeEnum(String type, int index) {
        this.type = type;
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
