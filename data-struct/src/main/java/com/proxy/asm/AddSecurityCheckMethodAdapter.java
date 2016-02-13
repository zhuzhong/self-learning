package com.proxy.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AddSecurityCheckMethodAdapter extends MethodAdapter {
	public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
		super(mv);
	}

	public void visitCode() {
		visitMethodInsn(Opcodes.INVOKESTATIC, // "SecurityChecker",
				// SecurityChecker.class.getName(),
				"com/proxy/asm/SecurityChecker", "checkSecurity", "()V");
	}

	private String changeString(String str) {
		String result;
		StringBuffer sb = new StringBuffer();
		String ars[] = str.split(".");
		for (String a : ars) {
			sb.append(a);
			sb.append("/");
		}
		result = sb.toString();

		
		return result;
	}
}