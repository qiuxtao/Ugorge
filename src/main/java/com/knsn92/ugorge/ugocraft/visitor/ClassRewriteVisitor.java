package com.knsn92.ugorge.ugocraft.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;

public class ClassRewriteVisitor extends ClassVisitor implements Opcodes {

    public ClassRewriteVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (!name.equals("transform"))
            return mv;
        mv.visitCode();

        // Inject early return for SoundManager to avoid breaking Forge sound events
        org.objectweb.asm.Label continueLabel = new org.objectweb.asm.Label();
        org.objectweb.asm.Label returnOriginalLabel = new org.objectweb.asm.Label();

        // Check if className equals "bjb"
        mv.visitVarInsn(ALOAD, 2);
        mv.visitLdcInsn("bjb");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
        mv.visitJumpInsn(IFNE, returnOriginalLabel);

        // Check if className equals "net/minecraft/client/audio/SoundManager"
        mv.visitVarInsn(ALOAD, 2);
        mv.visitLdcInsn("net/minecraft/client/audio/SoundManager");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
        mv.visitJumpInsn(IFEQ, continueLabel); // If neither, continue normally

        mv.visitLabel(returnOriginalLabel);
        mv.visitVarInsn(ALOAD, 5); // Load basicClass (byte[])
        mv.visitInsn(ARETURN);

        mv.visitLabel(continueLabel);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitLdcInsn("/");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "lastIndexOf", "(Ljava/lang/String;)I", false);
        mv.visitInsn(ICONST_1);
        mv.visitInsn(IADD);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "substring", "(I)Ljava/lang/String;", false);
        mv.visitVarInsn(ASTORE, 2);

        mv.visitEnd();
        return mv;
    }

}

