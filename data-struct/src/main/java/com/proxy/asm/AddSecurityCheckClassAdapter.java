package com.proxy.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class AddSecurityCheckClassAdapter extends ClassAdapter {

	private String enhancedSuperName;

	public AddSecurityCheckClassAdapter(ClassVisitor cv) {
		// Responsechain ����һ�� ClassVisitor���������ǽ����� ClassWriter��
		// �����д���������
		super(cv);
	}

	public void visit(final int version, final int access, final String name,
			final String signature, final String superName,
			final String[] interfaces) {
		String enhancedName = name + "$EnhancedByASM"; // �ı�������
		enhancedSuperName = name; // �ı丸�࣬�����ǡ�Account��
		super.visit(version, access, enhancedName, signature,
				enhancedSuperName, interfaces);
	}

	// ��д visitMethod�����ʵ� "operation" ����ʱ��
	// ����Զ��� MethodVisitor��ʵ�ʸ�д��������
	public MethodVisitor visitMethod2(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		MethodVisitor wrappedMv = mv;
		if (mv != null) {
			// ���� "operation" ����
			if (name.equals("operation")) {
				// ʹ���Զ��� MethodVisitor��ʵ�ʸ�д��������
				wrappedMv = new AddSecurityCheckMethodAdapter(mv);
			}
		}
		return wrappedMv;
	}

	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		MethodVisitor wrappedMv = mv;
		if (mv != null) {
			if (name.equals("operation")) {
				wrappedMv = new AddSecurityCheckMethodAdapter(mv);
			} else if (name.equals("<init>")) {
				wrappedMv = new ChangeToChildConstructorMethodAdapter(mv,
						enhancedSuperName);
			}
		}
		return wrappedMv;
	}
}