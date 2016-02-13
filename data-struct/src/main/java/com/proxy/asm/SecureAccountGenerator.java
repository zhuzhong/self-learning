package com.proxy.asm;

import java.io.IOException;

import org.objectweb.asm.*;

public class SecureAccountGenerator {

	private static AccountGeneratorClassLoader classLoader = new AccountGeneratorClassLoader();

	private static Class secureAccountClass;

	public Account generateSecureAccount() throws ClassFormatError,
			InstantiationException, IllegalAccessException, IOException {
		if (null == secureAccountClass) {
			ClassReader cr = new ClassReader("com/proxy/asm/Account");
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
			cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
			byte[] data = cw.toByteArray();
			secureAccountClass = classLoader.defineClassFromClassFile(
					//"Account$EnhancedByASM",
					"com.proxy.asm.Account$EnhancedByASM",
					//"com/proxy/asm/Account$EnhancedByASM",
					data);
		}
		return (Account) secureAccountClass.newInstance();
	}

	private static class AccountGeneratorClassLoader extends ClassLoader {
		public Class defineClassFromClassFile(String className, byte[] classFile)
				throws ClassFormatError {
			return defineClass(//"Account$EnhancedByASM"
					className
					, classFile, 0,
					classFile.length);
		}
	}
}