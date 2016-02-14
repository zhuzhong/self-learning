/**
 * 
 */
package com.zz.learning.netty5.chap12;

/**
 * @author sunff
 *
 */
public enum LoginResult {
    LOGIN_SUCCESS((byte) 0),

    lOGIN_FAILED((byte) -1);

    private byte value;

    private LoginResult(byte value) {
        this.value = value;
    }

    public byte value() {
        return this.value;
    }
}
