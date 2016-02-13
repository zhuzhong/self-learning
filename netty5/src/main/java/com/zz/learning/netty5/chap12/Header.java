/**
 * 
 */
package com.zz.learning.netty5.chap12;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunff
 *
 */
public final class Header {
    private int crcCode = 0xabef0101;
    // 消息长度
    private int length;
    // 会话id
    private long sessionID;
    // 消息类型
    private byte type;
    // 消息优先级
    private byte priority;

    // 附件
    private Map<String, Object> attachement = new HashMap<String, Object>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachement() {
        return attachement;
    }

    public void setAttachement(Map<String, Object> attachement) {
        this.attachement = attachement;
    }

    public String toString() {
        return "Header [crcCode=" + this.crcCode + ",length=" + this.length + ",sessionID=" + this.sessionID + ",type="
                + this.type + ",priority=" + this.priority + ",attachement=" + this.attachement + " ]";
    }

}
