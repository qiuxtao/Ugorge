package com.knsn92.ugorge.ugocraft.visitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SoundFixVisitor extends ClassVisitor implements Opcodes {

    private String className;

    public SoundFixVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

        // Strip Byakhee.iris() to prevent it from calling Minecraft.refreshResources()
        // on a background thread
        // which completely corrupts Forge's OpenAL sound system context and silences
        // the game.
        if ("net/maocat/Loader/Process/Client/Byakhee".equals(this.className)) {
            if ("iris".equals(name) || "anemone".equals(name)) {
                // To avoid 'unreachable code' VerifyError, we must discard all original
                // instructions
                // and just emit an empty method with a RETURN instruction.
                return new MethodVisitor(ASM5, mv) {
                    @Override
                    public void visitCode() {
                        super.visitCode();
                    }

                    @Override
                    public void visitInsn(int opcode) {
                    }

                    @Override
                    public void visitIntInsn(int opcode, int operand) {
                    }

                    @Override
                    public void visitVarInsn(int opcode, int var) {
                    }

                    @Override
                    public void visitTypeInsn(int opcode, String type) {
                    }

                    @Override
                    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                    }

                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                    }

                    @Override
                    public void visitInvokeDynamicInsn(String name, String desc, org.objectweb.asm.Handle bsm,
                            Object... bsmArgs) {
                    }

                    @Override
                    public void visitJumpInsn(int opcode, org.objectweb.asm.Label label) {
                    }

                    @Override
                    public void visitLabel(org.objectweb.asm.Label label) {
                    }

                    @Override
                    public void visitLdcInsn(Object cst) {
                    }

                    @Override
                    public void visitIincInsn(int var, int increment) {
                    }

                    @Override
                    public void visitTableSwitchInsn(int min, int max, org.objectweb.asm.Label dflt,
                            org.objectweb.asm.Label... labels) {
                    }

                    @Override
                    public void visitLookupSwitchInsn(org.objectweb.asm.Label dflt, int[] keys,
                            org.objectweb.asm.Label[] labels) {
                    }

                    @Override
                    public void visitMultiANewArrayInsn(String desc, int dims) {
                    }

                    @Override
                    public void visitTryCatchBlock(org.objectweb.asm.Label start, org.objectweb.asm.Label end,
                            org.objectweb.asm.Label handler, String type) {
                    }

                    @Override
                    public void visitLocalVariable(String name, String desc, String signature,
                            org.objectweb.asm.Label start, org.objectweb.asm.Label end, int index) {
                    }

                    @Override
                    public void visitLineNumber(int line, org.objectweb.asm.Label start) {
                    }

                    @Override
                    public void visitMaxs(int maxStack, int maxLocals) {
                        super.visitInsn(RETURN);
                        super.visitMaxs(0, 0);
                    }
                };
            }
        }
        return mv;
    }
}
